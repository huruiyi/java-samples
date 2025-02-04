package com.tomekl007.packt.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.tomekl007.packt.model.TravelDto;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveTravelRepositoryTest {

  @Test
  public void shouldCreateAndRetrieveTravel() {
    //given
    RestTemplate restTemplate = new RestTemplate();
    TravelDto travelDto = new TravelDto(UUID.randomUUID().toString(), "SRC", "DST");
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<TravelDto> entity = new HttpEntity<>(travelDto, httpHeaders);

    //when
    ResponseEntity<TravelDto> response = restTemplate.postForEntity("http://localhost:8080/reactive/travel", entity, TravelDto.class);

    //then
    assertThat(response.getStatusCode().value()).isEqualTo(200);
    assertThat(response.getBody()).isNotNull();

    //when
    List<TravelDto> getResponse = restTemplate.exchange("http://localhost:8080/reactive/travel/" + response.getBody().getUserId(),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<TravelDto>>() {
        }).getBody();

    //then
    assertThat(response.getStatusCode().value()).isEqualTo(200);
    assertThat(getResponse.size()).isGreaterThan(0);

  }

}
