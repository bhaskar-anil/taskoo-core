package in.taskoo.core.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Qualifier("searchApiTemplate")
public class SearchApiTemplate extends ExternalApiTemplate {
  @Value("${es.search.api.proxy.scheme}")
  private String proxySchme;

  @Value("${es.search.api.proxy.hostname}")
  private String proxyHost;

  @Value("${es.search.api.proxy.port}")
  private String proxyPort;

  @Value("${es.search.api.connect.timeout}")
  private String connectTimeout;

  @Value("${es.search.api.read.timeout}")
  private String readTimeout;

  @PostConstruct
  private void init() {
    init(proxySchme, proxyHost, proxyPort, connectTimeout, readTimeout);
  }
}