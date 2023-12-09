package com.example.petstore;

import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
class PetStoreApplicationTests {

  @Test
  public void contextLoads() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    String result = encoder.encode("myPassword");
    log.info(result);
    assertTrue(encoder.matches("myPassword", result));
  }

}
