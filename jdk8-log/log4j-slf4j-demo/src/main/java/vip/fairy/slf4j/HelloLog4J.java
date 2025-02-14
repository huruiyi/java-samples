package vip.fairy.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloLog4J {

  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloLog4J.class);
    logger.trace("TRACE..");
    logger.debug("DEBUG..");
    logger.info("INFO..");
    logger.warn("WARN..");
    logger.error("ERROR..");
  }

}
