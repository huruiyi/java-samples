package vip.fairy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootOauth2KeycloakApplication {

  /**
   * admin-cli，需要开启Standard flow
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringBootOauth2KeycloakApplication.class, args);
  }

}
