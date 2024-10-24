package org.javaboy.github_login.cotroller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/oauth")
public class GithubController {


  private static final String CLIENT_ID = "Iv23liZkVsiaLbwwejn5";
  private static final String CLIENT_SECRET = "4b58ce0507d49f0f54aaa429ec78f69fcd3d451f";
  private static final String CLIENT_REDIRECT_URI = "http://localhost:8080/oauth/github/callback";
  private static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
  private static final String GITHUB_USER_URL = "https://api.github.com/user";
  private static final String GITHUB_REPOS_URL = "https://api.github.com/users/huruiyi/repos";

  @Autowired
  private RestTemplate restTemplate;


  @GetMapping("/github/callback")
  public String authorization_code(String code, String state) throws JsonProcessingException {
    String accessToken = getAccessToken(code, state);

    getUserInfo(accessToken);
    getUserRepos(accessToken);

    return "forward:/index.html";
  }

  private String getAccessToken(String code, String state) {
    Map<String, String> map = new HashMap<>();
    map.put("client_id", CLIENT_ID);
    map.put("client_secret", CLIENT_SECRET);
    map.put("redirect_uri", CLIENT_REDIRECT_URI);
    map.put("state", state);
    map.put("code", code);
    Map<String, String> resp = restTemplate.postForObject(GITHUB_ACCESS_TOKEN_URL, map, Map.class);

    System.out.println(resp);

    if (resp != null) {
      return resp.get("access_token");
    } else {
      return null;
    }
  }

  void getUserInfo(String accessToken) throws JsonProcessingException {
    HttpHeaders httpheaders = new HttpHeaders();
    httpheaders.add("Authorization", "token " + accessToken);
    HttpEntity<?> httpEntity = new HttpEntity<>(httpheaders);
    ResponseEntity<Map> exchange = restTemplate.exchange(GITHUB_USER_URL, HttpMethod.GET, httpEntity, Map.class);
    System.out.println("exchange.getBody() = " + new ObjectMapper().writeValueAsString(exchange.getBody()));
  }


  void getUserRepos(String accessToken) throws JsonProcessingException {
    HttpHeaders httpheaders = new HttpHeaders();
    httpheaders.add("Authorization", "token " + accessToken);
    HttpEntity<?> httpEntity = new HttpEntity<>(httpheaders);
    ResponseEntity<Object> exchange = restTemplate.exchange(GITHUB_REPOS_URL, HttpMethod.GET, httpEntity, Object.class);
    System.out.println("exchange.getBody() = " + new ObjectMapper().writeValueAsString(exchange.getBody()));
  }
}
