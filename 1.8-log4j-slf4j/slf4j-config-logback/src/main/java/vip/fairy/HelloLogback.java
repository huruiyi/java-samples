package vip.fairy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloLogback {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HelloLogback.class);
        logger.info("This is how you configure Logback with SLF4J");
    }
}
