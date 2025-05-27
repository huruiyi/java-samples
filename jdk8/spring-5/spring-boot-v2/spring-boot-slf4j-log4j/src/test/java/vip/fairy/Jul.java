package vip.fairy;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.logging.*;

/**
 * JUL全称Java util Logging是java原生的日志框架，使用时不需要另外引用第三方类库
 */
class Jul {
    @Test
    void test1() {
        // 1.获取日志记录器对象
        Logger logger = Logger.getLogger("jul");
        // 2.日志记录输出
        logger.info("jul");
    }

    @Test
    void test2() {
        // 1.获取日志对象
        Logger logger = Logger.getLogger("jul");
        // 2.日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    @Test
    void test3() throws IOException {
        // 1.创建日志记录器对象
        Logger logger = Logger.getLogger("jul");

        // 一、自定义日志级别
        // a.关闭系统默认配置
        logger.setUseParentHandlers(false);
        // b.创建handler对象
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // c.创建formatter对象(简单格式转换对象)
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // d.进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);
        // e.设置日志级别
        logger.setLevel(Level.CONFIG);
        consoleHandler.setLevel(Level.INFO);

        // 二、输出到日志文件
        FileHandler fileHandler = new FileHandler("jul.log");
        fileHandler.setFormatter(simpleFormatter);

        //All Levels:OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL
        logger.addHandler(fileHandler);
        // 2.日志记录输出:
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }
}
