package com.packtpub.angularspringbook.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:custom.properties")
public class GreetingApplication {

  public static void main(String[] args) {
    SpringApplication.run(GreetingApplication.class, args);
  }

}
