package com.example.test;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;


public class IntegrationOneTest {

  private final Logger logger = LoggerFactory.getLogger(IntegrationOneTest.class);

  @Test
  public void test1One() {
    Flux<String> numbers = Flux.just("1", "2", "3");
    Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(2));
    Flux.zip(numbers, periodFlux).map(Tuple2::getT1).doOnNext(logger::info);
  }

  @Test
  public void test2One() {
    Flux<String> numbers = Flux.just("11", "22", "33");
    Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(2));
    Flux.zip(numbers, periodFlux).map(Tuple2::getT1).doOnNext(logger::info);
  }

}
