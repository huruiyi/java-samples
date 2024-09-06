package com.example.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.fairy")
public class FairyProperties {

  private final User user = new User();

  @Data
  public static class User {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;
    private String phone;
    private String address;
    private String city;
    private String country;
    private String zip;
    private String profileImage;
    private String coverImage;
    private String bio;
    private String website;
  }
}
