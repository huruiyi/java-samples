package com.example;

import com.example.config.ServicesConfig;
import com.example.config.XAJpaConfig;
import com.example.entities.Singer;
import com.example.services.SingerService;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TxJtaDemo {

  private static final Logger logger = LoggerFactory.getLogger(TxJtaDemo.class);

  public static void main(String... args) {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, XAJpaConfig.class);

    Singer singer = new Singer();
    singer.setFirstName("John");
    singer.setLastName("Mayer");
    singer.setBirthDate(new Date((new GregorianCalendar(1977, 9, 16)).getTime().getTime()));

    SingerService singerService = ctx.getBean(SingerService.class);
    singerService.save(singer);

    if (singer.getId() != null) {
      logger.info("--> Singer saved successfully");
    } else {
      logger.error("--> Singer was not saved, check the configuration!!");
    }
    List<Singer> singers = singerService.findAll();
    if (singers.size() != 2) {
      logger.error("--> Something went wrong.");
    } else {
      logger.info("--> Singers from both DBs: {}", singers);
    }

    ctx.close();
  }
} 
