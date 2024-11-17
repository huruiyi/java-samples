package com.example.demo;

import com.example.demo.properties.FairyProperties;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootSimpleApplication implements CommandLineRunner {

  @Resource
  private FairyProperties fairyProperties;

  public static void main(String[] args) {
    SpringApplication.run(BootSimpleApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.printf(fairyProperties.toString());
  }
}
