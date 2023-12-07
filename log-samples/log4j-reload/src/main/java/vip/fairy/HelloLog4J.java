package vip.fairy;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class HelloLog4J {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(HelloLog4J.class);
        logger.trace("Log4J trace");
        logger.debug("Log4J debug");
        logger.info("Log4J info");
        logger.warn("Log4J warn");
        logger.error("Log4J error");
        logger.fatal("Log4J fatal");
    }
}
