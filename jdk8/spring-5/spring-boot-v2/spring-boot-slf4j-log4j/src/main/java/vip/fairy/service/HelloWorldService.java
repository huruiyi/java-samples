package vip.fairy.service;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;


@Log4j2
@Component
public class HelloWorldService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${name:World}")
    private String name;

    public String getHelloMessage() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            LOGGER.info("LOGGER:SpringBoot 整合 log4j2 + 彩色打印,Hello World:https://www.baeldung.com/spring-boot-auto-configuration-report");
            log.info("log:SpringBoot 整合 log4j2 + 彩色打印,Hello World:https://www.baeldung.com/spring-boot-auto-configuration-report");
        }
        return "Hello " + this.name;
    }
}
