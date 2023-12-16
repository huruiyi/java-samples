package vip.fairy.controller;


import vip.fairy.client.RickAndMortyClient;
import vip.fairy.response.CharacterResponse;
import vip.fairy.response.ListOfEpisodesResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/webclient")
@AllArgsConstructor
public class RickAndMortyController {

  RickAndMortyClient rickAndMortyClient;

  @GetMapping("/character/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<CharacterResponse> getCharacterById(@PathVariable String id) {
    return rickAndMortyClient.findACharacterById(id);
  }

  @GetMapping("/episodes")
  @ResponseStatus(HttpStatus.OK)
  public Flux<ListOfEpisodesResponse> ListAllEpisodes() {
    return rickAndMortyClient.ListAllEpisodes();
  }

}
