package com.example.repos;

import com.example.entities.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveSingerRepoImpl implements ReactiveSingerRepo {

  @Autowired
  SingerRepository singerRepository;

  @Override
  public Mono<Singer> findById(Long id) {
    return Mono.justOrEmpty(singerRepository.findById(id));
  }

  @Override
  public Flux<Singer> findAll() {
    return Flux.fromIterable(singerRepository.findAll());
  }

  @Override
  public Mono<Void> save(Mono<Singer> singerMono) {
    return singerMono.doOnNext(singer ->
        singerRepository.save(singer)
    ).thenEmpty((Mono.empty()));
  }
}
