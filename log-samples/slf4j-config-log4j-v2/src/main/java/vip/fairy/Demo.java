package vip.fairy;

import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {

      private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        Configurator.initialize("TestClass", "log4j2.xml");

    LOGGER.info("Info level log message");
    LOGGER.debug("Debug level log message");
    LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
    }
}
