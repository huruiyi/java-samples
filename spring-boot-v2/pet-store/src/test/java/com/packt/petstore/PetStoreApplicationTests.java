package com.packt.petstore;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class PetStoreApplicationTests {

  @Test
  public void contextLoads() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    String result = encoder.encode("myPassword");
    assertTrue(encoder.matches("myPassword", result));
  }



}
