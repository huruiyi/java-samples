package com.example;

import com.example.config.DataJpaConfig;
import com.example.config.ServicesConfig;
import com.example.entities.Singer;
import com.example.services.SingerService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TxAnnotationDemo {

  private static final Logger logger = LoggerFactory.getLogger(TxAnnotationDemo.class);

  public static void main(String... args) {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, DataJpaConfig.class);

    SingerService singerService = ctx.getBean(SingerService.class);

    List<Singer> singers = singerService.findAll();
    singers.forEach(singer -> logger.info("singer-{}", singer));

    Singer singer = singerService.findById(1L);
    singer.setFirstName("John Clayton");
    singer.setLastName("Mayer");
    singerService.save(singer);

    logger.info("Singer saved successfully:{} ", singer);
    logger.info("Singer count: {}", singerService.countAll());

    ctx.close();
  }
}

