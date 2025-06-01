package vip.fairy.web;

import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class ApiController {

  @Value("${keycloak.server-url}")
  public String serverUrl;

  @Value("${keycloak.realm}")
  public String realm;

  private final WebClient webClient;

  @Autowired
  public ApiController(WebClient webClient) {
    this.webClient = webClient;
  }

  @GetMapping("/call-api")
  public String callApi(@RegisteredOAuth2AuthorizedClient("keycloak") OAuth2AuthorizedClient authorizedClient, Model model) {
    // 使用 WebClient 调用 API
    Mono<String> response = webClient.get()
        .uri(MessageFormat.format("{0}/admin/realms/{1}/users/{2}",
            serverUrl, realm, "bb531f1e-1bdb-4106-b9ed-2c9d2774d413")) // 替换为实际 API 地址
        .retrieve()
        .bodyToMono(String.class)
        .onErrorResume(e -> Mono.just("API调用失败: " + e.getMessage()));

    // 将响应添加到模型
    model.addAttribute("apiResponse", response.block());
    model.addAttribute("accessToken", authorizedClient.getAccessToken().getTokenValue());

    return "api-result";
  }

  @GetMapping("/")
  public String home() {
    return "home";
  }
}
