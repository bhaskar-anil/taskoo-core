package in.taskoo.core.repository.es;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import in.taskoo.core.response.dto.TaskIds;
import in.taskoo.core.response.dto.TaskResponseDto;
import in.taskoo.core.util.AuthorizationHeaders;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class TaskSearchRepositoryImpl implements TaskSearchRepository {

  @Qualifier("searchApiTemplate")
  private final RestTemplate searchApiTemplate;

  @Value("${es.base}")
  private String searchBaseUri;

  @Value("${es.task.search}")
  private String taskSearchEndpoint;

  @Value("${es.task.create}")
  private String taskCreateEndpoint;

  @Value("${es.task.update}")
  private String taskUpdateEndpoint;

  @Override
  public TaskIds search(String query, Pageable pageable) throws Exception {
    TaskIds response = null;
    try {
      HttpHeaders headers = AuthorizationHeaders.create();
      UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(this.searchBaseUri)
          .path(this.taskSearchEndpoint).queryParam("query", query);
      response = searchApiTemplate
          .exchange(uriBuilder.toUriString(), HttpMethod.GET, new HttpEntity<>(headers), TaskIds.class)
          .getBody();
    } catch (Exception e) {
      // TODO write external api call exception
    }
    return response;
  }

  @Override
  public void create(TaskResponseDto taskResponseDto) throws Exception {
    try {
      HttpHeaders headers = AuthorizationHeaders.create();
      UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(this.searchBaseUri)
          .path(this.taskCreateEndpoint);
      searchApiTemplate
          .exchange(uriBuilder.toUriString(), HttpMethod.PUT, new HttpEntity<>(taskResponseDto, headers), TaskResponseDto.class);
    } catch (Exception e) {
      // TODO write external api call exception
    }
  }

  @Override
  public void update(TaskResponseDto taskResponseDto) throws Exception {
    try {
      HttpHeaders headers = AuthorizationHeaders.create();
      UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(this.searchBaseUri)
          .path(this.taskCreateEndpoint);
      searchApiTemplate
          .exchange(uriBuilder.toUriString(), HttpMethod.PUT, new HttpEntity<>(taskResponseDto, headers), TaskResponseDto.class);
    } catch (Exception e) {
      // TODO write external api call exception
    }
  }
}
