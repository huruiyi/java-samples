package vip.fairy.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TokenController {

  @Value("${keycloak.token-url}")
  public String tokenUrl;

  @Value("${keycloak.client-id}")
  public String clientId;

  @Value("${keycloak.client-secret}")
  public String clientSecret;

  @Value("${keycloak.username}")
  public String username;

  @Value("${keycloak.password}")
  public String password;


  private final OAuth2AuthorizedClientService authorizedClientService;

  public TokenController(OAuth2AuthorizedClientService authorizedClientService) {
    this.authorizedClientService = authorizedClientService;
  }

  @ResponseBody
  @GetMapping("/token-v1")
  public HashMap<String, String> getToken(OAuth2AuthenticationToken authentication) {
    OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(),
        authentication.getName());
    String tokenValue = client.getAccessToken().getTokenValue();
    HashMap<String, String> token = new HashMap<>();
    token.put("token", tokenValue);
    return token;
  }

  @ResponseBody
  @GetMapping("/token-v2")
  public HashMap<String, String> getToken(@AuthenticationPrincipal OidcUser principal) {
    String tokenValue = principal.getIdToken().getTokenValue();
    HashMap<String, String> token = new HashMap<>();
    token.put("token", tokenValue);
    return token;
  }

  @ResponseBody
  @GetMapping("/token-v3")
  public Map<String, Object> getManualToken() {
    RestTemplate restTemplate = new RestTemplate();
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("client_id", clientId);
    params.add("client_secret", clientSecret);
    params.add("grant_type", AuthorizationGrantType.CLIENT_CREDENTIALS.getValue());
    params.add("username", username);
    params.add("password", password);
    Map<String, Object> response = restTemplate.postForObject(tokenUrl, params, Map.class);
    return response;
  }
}
