package com.example;

import com.example.web.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan(basePackages = {"com.example"})
@Configuration
public class ServerConfig {

  @Bean
  public Server server() {
    return new Server();
  }
}
