package vip.fairy;

import org.apache.log4j.Logger;
import vip.fairy.utils.PathUtils;

public class Log4jConfigReloadExample {

  public static void main(String[] args) throws InterruptedException {
    Log4jConfigurator.getInstance().initilize(PathUtils.getConfigPath());
    //https://howtodoinjava.com/log4j2/reload-log4j-on-runtime/
    //Get logger instance
    Logger LOGGER = Logger.getLogger(Log4jConfigReloadExample.class);

    //Print the log messages and wait for log4j changes
    while (true) {
      //Debug level log message
      LOGGER.debug("A debug message !!");
      //Info level log message
      LOGGER.info("A info message !!");

      //Wait between log messages
      Thread.sleep(2000);
    }
  }
}
