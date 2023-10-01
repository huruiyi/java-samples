package com.example;


import com.example.entities.Singer;
import com.example.services.SingerService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TxDeclarativeDemo {

  private static final Logger logger = LoggerFactory.getLogger(TxDeclarativeDemo.class);

  public static void main(String... args) {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/tx-declarative-app-context.xml");
    ctx.refresh();

    SingerService singerService = ctx.getBean(SingerService.class);

    List<Singer> singers = singerService.findAll();
    singers.forEach(singer -> logger.info("singer-{}", singer));

    Singer singer = singerService.findById(1L);
    singer.setFirstName("John Clayton");
    singerService.save(singer);
    logger.info("Singer saved successfully:{} ", singer);
    logger.info("Singer count: {}", singerService.countAll());

    ctx.close();
  }
}
