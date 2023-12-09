package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.entities.Singer;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class AnotherSingerHandlerTest {

  private static WebTestClient client;

  @BeforeAll
  public static void init() {
    client = WebTestClient
        .bindToServer()
        .baseUrl("http://localhost:9090")
        .build();
  }

  @Test
  public void getSingerNotFound() throws Exception {
    client.get().uri("/singers/99").exchange().expectStatus().isNotFound()
        .expectBody().isEmpty();
  }

  @Test
  public void getSingerFound() throws Exception {
    client.get().uri("/singers/1").exchange().expectStatus().isOk()
        .expectBody(Singer.class).consumeWith(seer -> {
          Singer singer = seer.getResponseBody();
          assertAll("singer", () ->
          {
            assertNotNull(singer);
            assertAll("singer",
                () -> assertEquals("John", singer.getFirstName()),
                () -> assertEquals("Mayer", singer.getLastName()));
          });
        });
  }

  @Test
  public void getAll() throws Exception {
    client.get().uri("/singers").accept(MediaType.TEXT_EVENT_STREAM)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBodyList(Singer.class).consumeWith(Assertions::assertNotNull);
  }

  @Test
  public void create() throws Exception {
    Singer singer = new Singer();
    singer.setFirstName("Ed");
    singer.setLastName("Sheeran");
    singer.setBirthDate(new Date(
        (new GregorianCalendar(1991, 2, 17)).getTime().getTime()));

    client.post().uri("/singers").body(Mono.just(singer), Singer.class).exchange().expectStatus().isOk();
  }

  @Test
  public void testCustomRouting() {
    RouterFunction function = RouterFunctions.route(
        RequestPredicates.GET("/test"),
        request -> ServerResponse.ok().build()
    );

    WebTestClient
        .bindToRouterFunction(function)
        .build().get().uri("/test")
        .exchange()
        .expectStatus().isOk()
        .expectBody().isEmpty();

  }
}
