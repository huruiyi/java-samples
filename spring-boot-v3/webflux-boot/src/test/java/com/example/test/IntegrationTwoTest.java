package com.example.test;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;


class IntegrationTwoTest {

  private final Logger logger = LoggerFactory.getLogger(IntegrationTwoTest.class);


  @Test
  public void test1Two() {
    Flux<String> numbers = Flux.just("a", "b", "c");
    Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(2));
    Flux.zip(numbers, periodFlux).map(Tuple2::getT1).doOnNext(logger::info);
  }

  @Test
  public void test2Two() {
    Flux<String> numbers = Flux.just("aa", "bb", "bb");
    Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(3));
    Flux.zip(numbers, periodFlux).map(Tuple2::getT1).doOnNext(logger::info);
  }
}
