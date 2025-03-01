package com.tomekl007.packt.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.tomekl007.packt.model.Travel;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelRepositoryIntegrationTest {

  @Autowired
  TravelRepository travelRepository;

  @Test
  public void shouldSaveTravelAndRetrieveItByUserId() {
    //given
    String userId = "joseph";
    Travel travel = new Travel(userId, "LONDON", "PARIS");

    //when
    travelRepository.save(travel);
    List<Travel> travels = travelRepository.findByUserId(userId);

    //then
    assertThat(travels.get(0).getDestination()).isEqualTo("PARIS");
    assertThat(travels.get(0).getSource()).isEqualTo("LONDON");
  }

  @Test
  public void shouldRetrieveAllTravelsThatHaveBarcelonaAsADestination() {
    //given
    List<Travel> travels = Arrays.asList(
        new Travel(UUID.randomUUID().toString(), "LONDON", "BARCELONA"),
        new Travel(UUID.randomUUID().toString(), "AMSTERDAM", "BARCELONA"),
        new Travel(UUID.randomUUID().toString(), "AMSTERDAM", "MADRID")
    );

    //when
    travelRepository.saveAll(travels);
    List<Travel> foundTravels = travelRepository.findByDestination("BARCELONA");

    //then
    assertThat(foundTravels.size()).isEqualTo(2);
  }

}
