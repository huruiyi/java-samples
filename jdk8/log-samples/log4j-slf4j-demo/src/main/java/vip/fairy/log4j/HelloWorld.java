package vip.fairy.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloWorld {

  private static final Logger logger = LogManager.getLogger(HelloWorld.class);

  public static void main(String[] args) {

    logger.trace("TRACE..");

    logger.debug("DEBUG..");
    logger.debug("Hello from Log4j 2");

    logger.info("INFO..");
    logger.warn("WARN..");
    logger.error("ERROR..");

    // in old days, we need to check the log level to increase performance
    if (logger.isDebugEnabled()) {
      logger.debug("{}", getNumber());
    }

    // with Java 8, we can do this, no need to check the log level
    logger.debug("{}", () -> getNumber());
    logger.debug("{}", HelloWorld::getNumber);
  }

  static int getNumber() {
    return 5;
  }

}
