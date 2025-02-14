package vip.fairy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloLog4J {

  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloLog4J.class);
    logger.trace("This is how you configure Log4J with SLF4J");
    logger.debug("This is how you configure Log4J with SLF4J");
    logger.info("This is how you configure Log4J with SLF4J");
    logger.warn("This is how you configure Log4J with SLF4J");
    logger.error("This is how you configure Log4J with SLF4J");
  }

}
