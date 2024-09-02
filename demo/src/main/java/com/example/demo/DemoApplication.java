package com.example.demo;

import com.example.demo.properties.FairyProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(FairyProperties.class)
public class DemoApplication implements CommandLineRunner {

  final FairyProperties fairyProperties;

  public DemoApplication(FairyProperties fairyProperties) {
    this.fairyProperties = fairyProperties;
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  public void run(String... args) {
    System.out.printf(fairyProperties.toString());
  }
}
