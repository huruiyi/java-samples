package com.example;

import com.example.entities.Singer;
import com.example.repos.SingerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.example")
public class SpringBootJpaApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootJpaApplication.class);
    @Autowired
    SingerRepository singerRepository;

    public static void main(String... args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootJpaApplication.class, args);

        System.in.read();
        ctx.close();
    }

    @Transactional(readOnly = true)
    @Override
    public void run(String... args) throws Exception {
        List<Singer> singers = singerRepository.findByFirstName("John");
        listSingersWithAlbum(singers);
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singers with instruments:");
        singers.forEach(singer -> {
            logger.info(singer.toString());
            if (singer.getAlbums() != null) {
                singer.getAlbums().forEach(album -> logger.info("\t" + album.toString()));
            }
            if (singer.getInstruments() != null) {
                singer.getInstruments().forEach(instrument -> logger.info("\t" + instrument.getInstrumentId()));
            }
        });
    }
}
