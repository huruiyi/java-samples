package com.example;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class FluxGenerator {

  public Flux<String> generate(String... args) {
    return Flux.just(args);
  }

}
