package com.example.unfiled;


import com.example.model.Greeting;
import com.example.model.Quote;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class Test_Rest {

  private static final Logger log = LoggerFactory.getLogger(Test_Rest.class);

  @Test
  public void Test1() {
    RestTemplate restTemplate = new RestTemplate();
    Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
    log.info(quote.toString());
  }

  public void Test2() {
    RestTemplate restTemplate = new RestTemplate();
    Greeting greeting = restTemplate.getForObject("http://localhost:8086/greeting", Greeting.class);
    log.info(greeting.toString());
  }


  @Test
  @DisplayName("并发限制测试")
  public void Test3() {
    RestTemplate restTemplate = new RestTemplate();
    for (int i = 0; i < 20; i++) {
      String result = restTemplate.getForObject("http://localhost:9102/testLimit", String.class);
      System.out.println(result);
    }
  }
}
