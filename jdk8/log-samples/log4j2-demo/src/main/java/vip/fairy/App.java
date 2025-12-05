package vip.fairy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) {
    //Configurator.initialize("TestClass", "log4j2.properties");

    // 记录debug级别的信息
    logger.debug("This is debug message.");
    // 记录info级别的信息
    logger.info("This is info message.");
    // 记录error级别的信息
    logger.error("This is error message.");

    for (int i = 0; i < 100000; i++) {
      logger.info("This is log message {}", i);
      logger.error("This is error message.");
    }
    System.out.println("Hello World!");
  }

}
