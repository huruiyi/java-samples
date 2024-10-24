package com.example;

import com.example.config.DataJpaConfig;
import com.example.config.ServicesConfig;
import com.example.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TxProgrammaticDemo {

  private static final Logger logger = LoggerFactory.getLogger(TxProgrammaticDemo.class);

  public static void main(String... args) {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, DataJpaConfig.class);
    
    SingerService singerService = ctx.getBean(SingerService.class);
    logger.info("Singer count:{} ", singerService.countAll());

    ctx.close();
  }
}
