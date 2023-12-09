package vip.fairy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vip.fairy.service.HelloWorldService;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
public class Slf4jLog4jSpringBootApplication implements CommandLineRunner {

    // SLF4J's logging instance for this class
    // We could have used LoggerFactory.getLogger(Slf4jSpringBootApplication.class) as well
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    // This is what SLF4J uses to bind to a specific logging implementation
    final StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();


    Logger logger = LoggerFactory.getLogger(Slf4jLog4jSpringBootApplication.class);

    private final HelloWorldService helloWorldService;

    public Slf4jLog4jSpringBootApplication(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    /**
     *本实例化，会加载log4j2-spring.xml
     *彩色日志： 修改jvm参数 -Dlog4j.skipJansi=false
     *
     * When using starters, Logback is used for logging by default.
     * logback-spring.xml
     * logback.xml
     * logback-spring.groovy
     * logback.groovy
     * <p>
     * spring-boot-starter-log4j2
     * <p>
     * log4j2-spring.xml
     * log4j2.xml
     */
    public void run(String... args) throws InterruptedException {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        System.out.println(binder.getLoggerFactory());
        System.out.println(binder.getLoggerFactoryClassStr());

        LOGGER.debug(this.helloWorldService.getHelloMessage());
        if (args.length > 0 && args[0].equals("exitcode")) {
            LOGGER.error("Exit Code encountered", new Exception());
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Slf4jLog4jSpringBootApplication.class, args);
    }
}
