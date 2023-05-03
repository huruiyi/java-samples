package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootJdbcApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootJdbcApplication.class);


    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootJdbcApplication.class, args);
        assert (ctx != null);

        SingerDao singerDao = ctx.getBean(SingerDao.class);

        System.out.println("*************************************************************");
        logger.info("Retrieved singer: " + singerDao.findNameById(1L));
        System.out.println("*************************************************************");

        System.out.println("*************************************************************");
        String firstName = singerDao.findFirstNameById(1L);
        logger.info("first name:" + firstName);
        System.out.println("*************************************************************");

        ctx.close();
    }

}
