package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisLimitApplication {

  /**
   * 暂时支持到：2.1.18.RELEASE
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(RedisLimitApplication.class, args);
  }
}
