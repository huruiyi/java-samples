package vip.fairy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class FileWatcherDemo {

    private static Logger logger = LoggerFactory.getLogger(FileWatcherDemo.class);

    /**
     * org.springframework.batch:spring-batch-core:4.3.10:schema-mysql.sql
     */
    public static void main(String... args) throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:spring/config.xml");
        assert (ctx != null);
        logger.info("Application started...");
        System.in.read();
        ctx.close();
    }
}

