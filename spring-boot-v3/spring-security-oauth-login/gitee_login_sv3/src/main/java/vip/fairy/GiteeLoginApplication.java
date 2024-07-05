package vip.fairy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GiteeLoginApplication {

  /**
   * <a href="https://gitee.com/lenve/java_training_2024.git">参考地址</a>
   */
  public static void main(String[] args) {
    SpringApplication.run(GiteeLoginApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
