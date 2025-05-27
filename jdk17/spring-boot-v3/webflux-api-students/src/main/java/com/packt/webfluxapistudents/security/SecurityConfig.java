package com.packt.webfluxapistudents.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.FormLoginSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//
//                .csrf().disable()
//                .authorizeExchange()
//                .pathMatchers("/").permitAll()
//                .anyExchange().authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin().disable()
//                .build();
//    }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    http.csrf(CsrfSpec::disable);
    http.authorizeExchange(authorizeExchangeSpec -> {
      authorizeExchangeSpec.pathMatchers("/").permitAll().anyExchange().permitAll();
    });
    http.httpBasic(HttpBasicSpec::disable);
    http.formLogin(FormLoginSpec::disable);
    return http.build();
  }

  @Bean
  public MapReactiveUserDetailsService reactiveUserDetailsService() {
    UserDetails user = User.builder()
        .username("user")
        .password("{noop}user")
        .roles("USER")
        .build();

    return new MapReactiveUserDetailsService(user);
  }


}
