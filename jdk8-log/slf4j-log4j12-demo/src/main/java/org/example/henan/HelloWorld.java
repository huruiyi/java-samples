package org.example.henan;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloWorld {

  private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

  public static void main(String[] args) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
    for (int i = 0; i < 10; i++) {
      logger.info("河南人：Hello World！！！世界你好！！！{}", format.format(LocalDateTime.now()));
    }
  }

}
