package vip.fairy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/css/**", "/webjars/**");
  }

  @Bean
  protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(requestMatcherRegistry -> {
          requestMatcherRegistry.requestMatchers("/", "/home").permitAll();
          requestMatcherRegistry.requestMatchers("/singers/**").authenticated();
          requestMatcherRegistry.anyRequest().authenticated();

        })
        .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login").permitAll())
        .logout(LogoutConfigurer::permitAll);

    return http.build();
  }

  @Bean
  UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
    users.createUser(User.withUsername("user").password("{noop}user").roles("USER").build());
    users.createUser(User.withUsername("江南一点雨").password("{noop}123").roles("admin").build());
    return users;
  }

}
