package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Demo2Application {
  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Demo2Application.class, args);
    String[] beanDefinitionNames = context.getBeanDefinitionNames();
    for (String name : beanDefinitionNames) {
      System.out.println(name);
    }
  }

}
