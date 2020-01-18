package in.taskoo.core.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

@Component
public class ExternalApiErrorHandler extends DefaultResponseErrorHandler {
  private static final Logger logger = LoggerFactory.getLogger(ExternalApiErrorHandler.class);

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    HttpStatus statusCode = response.getStatusCode();
    switch (statusCode.series()) {
    case CLIENT_ERROR:
      logger.warn("ExternalApiErrorHandler is4xxClientError --> statusCode = " + statusCode);
      throw new HttpClientErrorException(statusCode, response.getStatusText(), response.getHeaders(),
          getResponseBody(response), getCharset(response));
    case SERVER_ERROR:
      logger.warn("ExternalApiErrorHandler is5xxServerError --> statusCode = " + statusCode);
      throw new HttpServerErrorException(statusCode, response.getStatusText(), response.getHeaders(),
          getResponseBody(response), getCharset(response));
    default:
      logger.warn("ExternalApiErrorHandler unknownErrorCode --> statusCode = " + statusCode);
      throw new UnknownHttpStatusCodeException(statusCode.value(), response.getStatusText(), response.getHeaders(),
          getResponseBody(response), getCharset(response));
    }
  }

}
