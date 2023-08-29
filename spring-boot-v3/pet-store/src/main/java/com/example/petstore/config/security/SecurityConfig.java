package com.example.petstore.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Autowired
  private ReactiveUserDetailsService reactiveUserDetailsService;

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

    WebSessionServerLogoutHandler webSession = new WebSessionServerLogoutHandler();
    SecurityContextServerLogoutHandler securityContext = new SecurityContextServerLogoutHandler();
    DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(webSession, securityContext);

    ServerLogoutSuccessHandler logoutSuccessHandler =
        (exchange, authentication) -> Mono.fromRunnable(() -> exchange.getExchange().getResponse().setStatusCode(HttpStatus.OK));

    http.authorizeExchange((exchange) -> {
      exchange.pathMatchers(HttpMethod.POST, "/login").permitAll();
      exchange.pathMatchers(HttpMethod.POST, "/logout").permitAll();
      exchange.pathMatchers(HttpMethod.POST, "/register").permitAll();
      exchange.anyExchange().authenticated();
    });

    http.httpBasic(HttpBasicSpec::disable);

    http.csrf(CsrfSpec::disable);

    http.formLogin(formLoginSpec -> {
      formLoginSpec.authenticationManager(authenticationManager());
      formLoginSpec.requiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login"));
      formLoginSpec.authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/pets"));
      formLoginSpec.authenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler(new HttpBasicServerAuthenticationEntryPoint()));
      formLoginSpec.authenticationEntryPoint(new HttpBasicServerAuthenticationEntryPoint());
    });

    http.logout((logout) -> logout.logoutHandler(logoutHandler).logoutSuccessHandler(logoutSuccessHandler));
    return http.build();
  }


  @Bean
  public ReactiveAuthenticationManager authenticationManager() {
    UserDetailsRepositoryReactiveAuthenticationManager authManager = new UserDetailsRepositoryReactiveAuthenticationManager(
        reactiveUserDetailsService);
    authManager.setPasswordEncoder(passwordEncoder());
    return authManager;
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
