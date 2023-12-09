package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Application {

  private static final Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Application.class).run(args);
    assert (ctx != null);
    logger.info("Application started...");

    System.in.read();
    ctx.close();
  }

}
