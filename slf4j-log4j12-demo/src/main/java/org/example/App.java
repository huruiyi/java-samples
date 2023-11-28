package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * https://www.jianshu.com/p/ccafda45bcea
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        for (int i = 0; i < 100000; i++) {
            Thread.sleep(50);
            logger.info("Hello World！！！世界你好！！！" + format.format(LocalDateTime.now()));
        }
    }

}
