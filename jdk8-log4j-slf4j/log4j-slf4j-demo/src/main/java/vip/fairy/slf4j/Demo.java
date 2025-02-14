package vip.fairy.slf4j;

import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {

  private static final Logger logger = LoggerFactory.getLogger(Demo.class);

  public static void main(String[] args) {
    Configurator.initialize("TestClass", "log4j2-general.xml");

    logger.trace("TRACE..");
    logger.debug("DEBUG..");
    logger.info("INFO..");
    logger.warn("WARN..");

    logger.error("ERROR..");
    logger.error("Error Message Logged !!!", new NullPointerException("NullError"));
  }

}
