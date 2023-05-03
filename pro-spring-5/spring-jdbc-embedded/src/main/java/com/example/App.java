package com.example;

import com.example.dao.SingerDao;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/embedded-h2-cfg.xml");
        ctx.refresh();

         SingerDao singerDao = ctx.getBean(SingerDao.class);

        System.out.println("First name for singer id 1 is: " + singerDao.findFirstNameById(1L));

        ctx.close();
    }
}
