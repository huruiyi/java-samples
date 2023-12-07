package vip.fairy;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Objects;

public class HelloLog4J {
    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        String path = Objects.requireNonNull(HelloLog4J.class.getClassLoader().getResource("log4j.properties")).getPath();
        PropertyConfigurator.configure(path);
        Logger logger = Logger.getLogger(HelloLog4J.class);
        logger.debug(" debug ");
        logger.error(" error ");
    }

    static void test2() {
        Logger logger = Logger.getLogger(HelloLog4J.class);
        logger.trace("Log4J trace");
        logger.debug("Log4J debug");
        logger.info("Log4J info");
        logger.warn("Log4J warn");
        logger.error("Log4J error");
        logger.fatal("Log4J fatal");
    }
}
