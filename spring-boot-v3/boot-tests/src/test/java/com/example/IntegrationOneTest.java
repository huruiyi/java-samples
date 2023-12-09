package com.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class IntegrationOneTest {

  private final Logger logger = LoggerFactory.getLogger(IntegrationOneTest.class);
  @Autowired
  FluxGenerator fluxGenerator;

  @Test
  public void test1One() {
    fluxGenerator.generate("1", "2", "3").collectList().block().forEach(s ->
        executeSlow(2000, s)
    );
  }

  @Test
  public void test2One() {
    fluxGenerator.generate("11", "22", "33").collectList().block().forEach(s -> executeSlow(1000, s));
  }

  private void executeSlow(int duration, String s) {
    try {
      Thread.sleep(duration);
      logger.info(s);
    } catch (InterruptedException e) {
    }
  }
}

