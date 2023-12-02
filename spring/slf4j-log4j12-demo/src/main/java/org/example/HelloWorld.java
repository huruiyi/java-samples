package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloWorld {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    /**
     * https://www.jianshu.com/p/ccafda45bcea
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        for (int i = 0; i < 2; i++) {
            Thread.sleep(50);
            logger.info("中国人：Hello World！！！世界你好！！！" + format.format(LocalDateTime.now()));
        }
    }
}
