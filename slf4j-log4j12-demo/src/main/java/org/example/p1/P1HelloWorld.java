package org.example.p1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class P1HelloWorld {

    private static final Logger logger = LoggerFactory.getLogger(P1HelloWorld.class);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            logger.info("p1:Hello World!" + new Date());
        }
    }

}
