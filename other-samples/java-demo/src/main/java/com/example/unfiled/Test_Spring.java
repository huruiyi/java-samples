package com.example.unfiled;


import com.example.model.Greeting;
import com.example.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Test_Spring {

  private static final Logger log = LoggerFactory.getLogger(Test_Spring.class);

  public static void main(String args[]) {
    SpringApplication.run(Test_Spring.class);
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  @Bean
  public CommandLineRunner RestTemplateDemo0(RestTemplate restTemplate) {
    return args -> {
      Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
      System.out.println(quote.toString());
    };
  }

  @Bean
  public long RestTemplateDemo1() {
    RestTemplate restTemplate = new RestTemplate();
    Greeting res = restTemplate.getForObject("http://localhost:8086/greeting", Greeting.class);
    System.out.println(res);
    return 0;
  }
}
