package com.example;

import com.example.entities.Singer;
import com.example.repos.ReactiveSingerRepo;
import java.time.Duration;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Slf4j
@RestController
@SpringBootApplication
public class ReactiveApplication {

  final ReactiveSingerRepo reactiveSingerRepo;

  public ReactiveApplication(ReactiveSingerRepo reactiveSingerRepo) {
    this.reactiveSingerRepo = reactiveSingerRepo;
  }

  @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Singer> oneByOne() {
    Flux<Singer> singers = reactiveSingerRepo.findAll();
    Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(2));
    return Flux.zip(singers, periodFlux).map(Tuple2::getT1);
  }

  @GetMapping(value = "/one/{id}")
  public Mono<Singer> one(@PathVariable Long id) {
    return reactiveSingerRepo.findById(id);
  }

  public static void main(String... args) throws Exception {
    HashMap<String, Object> hashMap = new HashMap<>() {{
      put("server.port", "9090");
      put("spring.jpa.hibernate.ddl-auto", "create-drop");
    }};
    ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ReactiveApplication.class).properties(hashMap).run(args);
    assert (ctx != null);
    log.info("Application started...");
    System.in.read();
    ctx.close();
  }


  @Bean
  WebClient client() {
    return WebClient.create("http://localhost:9090");
  }

  @Bean
  CommandLineRunner clr(WebClient client) {
    return args -> client.get().uri("/all").accept(MediaType.TEXT_EVENT_STREAM).exchange().flatMapMany(cr -> cr.bodyToFlux(Singer.class))
        .subscribe(System.out::println);
  }
}
