package vip.fairy.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import vip.fairy.service.OtherService;


@Configuration(proxyBeanMethods = false)
@ImportResource({"classpath:/META-INF/context.xml"})
public class TestConfig {

  @Bean
  CommandLineRunner testCommandLineRunner(OtherService service) {
    return (args) -> System.out.println(service.getMessage());
  }

}
