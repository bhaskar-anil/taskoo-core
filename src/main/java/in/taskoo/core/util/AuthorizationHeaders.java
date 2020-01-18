package in.taskoo.core.util;

import org.springframework.http.HttpHeaders;

public class AuthorizationHeaders {
  public static HttpHeaders create() {
    // TODO add later
    // String userToken = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
    HttpHeaders headers = new HttpHeaders();
    // headers.set("Authorization", "Bearer " + userToken);
    return headers;
  }
}