package vip.fairy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwaggerUiApplication {

  // swagger-ui:
  //  http://localhost:8080/swagger-ui.html
  public static void main(String[] args) {
    SpringApplication.run(SwaggerUiApplication.class, args);
  }

}
