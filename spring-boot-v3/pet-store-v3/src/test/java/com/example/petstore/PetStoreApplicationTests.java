package com.example.petstore;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class PetStoreApplicationTests {

  @Test
  public void contextLoads() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    String result = encoder.encode("myPassword");
    assertTrue(encoder.matches("myPassword", result));
  }

}
