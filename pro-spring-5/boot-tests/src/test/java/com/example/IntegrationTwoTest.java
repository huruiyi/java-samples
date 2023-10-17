package com.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IntegrationTwoTest {

  private final Logger logger = LoggerFactory.getLogger(IntegrationTwoTest.class);

  @Autowired
  FluxGenerator fluxGenerator;

  @Test
  public void test1Two() {
    fluxGenerator.generate("a", "b", "c").collectList().block().forEach(logger::info);
  }

  @Test
  public void test2Two() {
    fluxGenerator.generate("aa", "bb", "cc").collectList().block().forEach(logger::info);
  }
}
