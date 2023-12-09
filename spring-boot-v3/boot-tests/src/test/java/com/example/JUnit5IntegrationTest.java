package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.config.TestConfig;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class JUnit5IntegrationTest {

  @Autowired
  FluxGenerator fluxGenerator;

  @Test
  public void testGenerator() {
    List<String> list = fluxGenerator.generate("2", "3").collectList().block();
    assertEquals(2, list.size());
  }
}
