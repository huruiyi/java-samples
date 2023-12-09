package com.example.repos;

import com.example.entities.Singer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveSingerRepo {

  Mono<Singer> findById(Long id);

  Flux<Singer> findAll();

  Mono<Void> save(Mono<Singer> singer);
}
