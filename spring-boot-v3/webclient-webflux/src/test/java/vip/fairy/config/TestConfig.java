package vip.fairy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.fairy.service.FluxGenerator;


@Configuration
public class TestConfig {

  @Bean
  FluxGenerator generator() {
    return new FluxGenerator();
  }

}
