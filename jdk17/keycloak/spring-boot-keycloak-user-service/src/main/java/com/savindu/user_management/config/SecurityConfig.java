package com.savindu.user_management.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

  public static final String ADMIN = "admin";
  public static final String GENERAL = "general";
  private final JwtAuthConverter jwtAuthConverter = new JwtAuthConverter();


  @Value("${keycloak.adminClientId}")
  private String adminClientId;

  @Value("${keycloak.adminClientSecret}")
  private String adminClientSecret;

  @Value("${keycloak.urls.auth}")
  private String authServerUrl;

  @Value("${keycloak.realm}")
  private String realm;

  /**
   * keycloak需要设置功能配置：服务账户角色
   */
  @Bean
  public Keycloak keycloak() {
    return KeycloakBuilder.builder()
        .serverUrl(authServerUrl)
        .realm(realm)
        .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
        .clientId(adminClientId)
        .clientSecret(adminClientSecret)
        .build();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.GET, "/test/anonymous", "/test/anonymous/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/test/admin", "/test/admin/**").hasRole(ADMIN)
            .requestMatchers(HttpMethod.GET, "/test/user").hasAnyRole(GENERAL)
            .anyRequest().authenticated()
        )
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> jwt
                .jwtAuthenticationConverter(jwtAuthConverter)
            )
        )
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> {
      web.ignoring().requestMatchers(
          HttpMethod.POST,
          "/public/**",
          "/users/**",
          "/api/v1/user-management/public/**"

      );
      web.ignoring().requestMatchers(
          HttpMethod.GET,
          "/public/**",
          "/api/v1/user-management/public/**"
      );
      web.ignoring().requestMatchers(
          HttpMethod.DELETE,
          "/public/**",
          "/api/v1/user-management/public/**"
      );
      web.ignoring().requestMatchers(
          HttpMethod.PUT,
          "/public/**",
          "/api/v1/user-management/public/**"
      );
      web.ignoring().requestMatchers(
              HttpMethod.OPTIONS,
              "/**"
          )
          .requestMatchers(
              "/v3/api-docs/**",
              "/configuration/**",
              "/swagger-ui/**",
              "/swagger-resources/**",
              "/swagger-ui.html",
              "/webjars/**",
              "/api-docs/**");
    };
  }


}
