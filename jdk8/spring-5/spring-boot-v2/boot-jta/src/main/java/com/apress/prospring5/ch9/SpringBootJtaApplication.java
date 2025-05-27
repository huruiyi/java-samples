package com.apress.prospring5.ch9;

import com.apress.prospring5.ch9.entities.Singer;
import com.apress.prospring5.ch9.services.SingerService;
import java.util.Date;
import java.util.GregorianCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.apress.prospring5.ch9.services")
public class SpringBootJtaApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(SpringBootJtaApplication.class);

    public static void main(String... args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootJtaApplication.class, args);

        System.in.read();
        ctx.close();
    }

    @Autowired
    SingerService singerService;

    @Override
    public void run(String... args) {
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        GregorianCalendar gregorianCalendar = new GregorianCalendar(1977, 9, 16);
        long time = gregorianCalendar.getTime().getTime();
        Date birthDate = new Date(time);
        singer.setBirthDate(birthDate);
        singerService.save(singer);

        long count = singerService.count();
        if (count == 1) {
            logger.info("--> Singer saved successfully");
        } else {
            logger.error("--> Singer was not saved, check the configuration!!");
        }
        System.out.println("*********************************************************************************");
        try {
            singerService.save(null);
        } catch (Exception ex) {
            logger.error(ex.getMessage() + "Final count:" + singerService.count());
        }
    }
}
