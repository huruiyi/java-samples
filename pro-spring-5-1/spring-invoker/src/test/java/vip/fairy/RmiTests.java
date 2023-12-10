package vip.fairy;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import vip.fairy.config.RmiClientConfig;
import vip.fairy.entities.Singer;
import vip.fairy.services.SingerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)  //junit4
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RmiClientConfig.class})
class RmiTests {
    private final Logger logger = LoggerFactory.getLogger(RmiTests.class);

    @Autowired
    private SingerService singerService;

    @Test
    void testRmiAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    void testRmiJohn() {
        List<Singer> singers = singerService.findByFirstName("John");
        assertEquals(2, singers.size());
        listSingers(singers);
    }

    private void listSingers(List<Singer> singers) {
        singers.forEach(s -> logger.info(s.toString()));
    }
}
