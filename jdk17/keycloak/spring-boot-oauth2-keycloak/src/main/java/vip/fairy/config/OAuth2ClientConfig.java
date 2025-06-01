package vip.fairy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OAuth2ClientConfig {

  @Bean
  public OAuth2AuthorizedClientService authorizedClientService(
      ClientRegistrationRepository clientRegistrationRepository) {
    return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
  }

  @Bean
  public OAuth2AuthorizedClientRepository authorizedClientRepository(
      OAuth2AuthorizedClientService authorizedClientService) {
    return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
  }


  // 配置 WebClient 支持 OAuth2
  @Bean
  public WebClient webClient(WebClient.Builder webClientBuilder,
      ClientRegistrationRepository clientRegistrationRepository,
      OAuth2AuthorizedClientRepository authorizedClientRepository) {

    ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
        new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, authorizedClientRepository);

    oauth2.setDefaultClientRegistrationId("keycloak");

    return webClientBuilder
        .apply(oauth2.oauth2Configuration())
        .build();
  }
}
