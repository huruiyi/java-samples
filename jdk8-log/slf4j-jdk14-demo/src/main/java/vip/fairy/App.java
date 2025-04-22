package vip.fairy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  public static void main(String[] args) {
    // 获取日志记录器
    Logger logger = LoggerFactory.getLogger(App.class);

    // 记录不同级别的日志
    logger.trace("This is a trace message.");
    logger.debug("This is a debug message.");
    logger.info("This is an info message.");
    logger.warn("This is a warn message.");
    logger.error("This is an error message.");

    // 带有参数的日志记录
    String name = "John";
    int age = 30;
    logger.info("Name: {}, Age: {}", name, age);

    // 带有异常的日志记录
    try {
      int result = 1 / 0;
    } catch (ArithmeticException e) {
      logger.error("An arithmetic exception occurred.", e);
    }
  }
}
