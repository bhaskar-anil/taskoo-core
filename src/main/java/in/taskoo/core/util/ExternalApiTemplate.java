package in.taskoo.core.util;

import static java.util.Arrays.asList;

import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class ExternalApiTemplate extends RestTemplate {
  @Autowired
  private ExternalApiErrorHandler externalApiErrorHandler;
  @Autowired
  private ExternalApiLoggingInterceptor externalApiLoggingInterceptor;

  protected void init(String proxySchme, String proxyHost, String proxyPort, String connectTimeout,
      String readTimeout) {
    setRequestFactory(httpRequestFactory(proxySchme, proxyHost, proxyPort, connectTimeout, readTimeout));

    ObjectMapper mapper = new ObjectMapper();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
    setMessageConverters(asList(new MappingJackson2HttpMessageConverter(mapper)));

    setErrorHandler(externalApiErrorHandler);

    setInterceptors(Collections.singletonList(externalApiLoggingInterceptor));
  }

  private ClientHttpRequestFactory httpRequestFactory(String proxySchme, String proxyHost, String proxyPort,
      String connectTimeout, String readTimeout) {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
        httpClient(proxySchme, proxyHost, proxyPort));

    if (StringUtils.isNotEmpty(connectTimeout)) {
      factory.setConnectTimeout(Integer.parseInt(connectTimeout));
    }

    if (StringUtils.isNotEmpty(readTimeout)) {
      factory.setReadTimeout(Integer.parseInt(readTimeout));
    }
    return new BufferingClientHttpRequestFactory(factory);
  }

  private CloseableHttpClient httpClient(String proxySchme, String proxyHost, String proxyPort) {
    HttpClientBuilder builder = HttpClientBuilder.create();
    builder.setConnectionManager(new PoolingHttpClientConnectionManager());

    if (StringUtils.isNotEmpty(proxyHost) && StringUtils.isNotEmpty(proxyPort) && StringUtils.isNotEmpty(proxySchme)) {
      builder.setProxy(new HttpHost(proxyHost, Integer.parseInt(proxyPort), proxySchme));
    }

    return builder.build();
  }
}