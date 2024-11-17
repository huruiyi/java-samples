package com.mastering.spring.reactive;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SpringReactiveTest {

  @Test
  public void monoExample() throws InterruptedException {
    Mono<String> stubMonoWithADelay = Mono.just("John").delayElement(Duration.ofSeconds(5));

    stubMonoWithADelay.subscribe(new SystemOutConsumer());
    stubMonoWithADelay.subscribe(new WelcomeConsumer());
    Thread.sleep(10000);
  }

  private static List<String> streamOfNames = Arrays.asList("John", "Adam", "Joe", "Doe", "Jane");

  @Test
  public void simpleFluxStream() {
    Flux<String> stubFluxStream = Flux.just("Jane", "Joe");
    stubFluxStream.subscribe(new SystemOutConsumer());
  }

  @Test
  public void fluxStreamWithDelay() throws InterruptedException {
    Flux<String> stubFluxWithNames = Flux.fromIterable(streamOfNames).delayElements(Duration.ofMillis(1000));
    stubFluxWithNames.subscribe(new SystemOutConsumer());
    stubFluxWithNames.subscribe(new WelcomeConsumer());
    Thread.sleep(10000);
  }
}
