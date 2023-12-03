package vip.fairy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class Log4j2Test {

    private final Logger logger = LogManager.getLogger(Log4j2Test.class);

    @Test
    void test1() {
        logger.debug("Debug Message Logged !!!");
        logger.info("Info Message Logged !!!");
        logger.error("Error Message Logged !!!", new NullPointerException("NullError"));
    }
}
