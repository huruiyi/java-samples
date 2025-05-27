package vip.fairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class HelloController {

  String subscriptionUrl = "https://gitee.com/api/v5/users/huruiyi/subscriptions";

  @Autowired
  RestTemplate restTemplate;

  @GetMapping("/user")
  public DefaultOAuth2User getUserInfo() {
    return (DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  @GetMapping(path = "/subscriptions", produces = "application/json")
  public String getToken2(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
    OAuth2AccessToken accessToken = client.getAccessToken();
    OAuth2RefreshToken refreshToken = client.getRefreshToken();
    System.out.println("accessToken = " + accessToken);
    System.out.println("refreshToken = " + refreshToken);

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(subscriptionUrl)
        .queryParam("access_token", accessToken.getTokenValue())
        .queryParam("sort", "last_push")
        .queryParam("direction", "desc");

    ResponseEntity<String> response = restTemplate.exchange(
        builder.toUriString(),
        HttpMethod.GET,
        null,
        String.class
    );
    System.out.println(response.getStatusCode());
    //JSON.toJSONString(response.getBody(), Feature.PrettyFormat)
    return response.getBody();
  }

  @GetMapping("/emails")
  @PreAuthorize("hasAuthority('SCOPE_emails')")
  public String emails() {
    return "Hello emails";
  }

  @GetMapping("/gists")
  @PreAuthorize("hasAuthority('SCOPE_gists')")
  public String gists() {
    return "Hello gists";
  }
}
