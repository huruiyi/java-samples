package vip.fairy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubboProviderApplication {

  /**
   * Zk Admin Server: http://localhost:8080/commands
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(DubboProviderApplication.class, args);
  }

}
