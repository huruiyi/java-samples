package vip.fairy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;

/**
 * JCL全称为Jakarta Commons Logging，是Apache提供的一个通用日志API。
 */
public class Jcl {
    @Test
    public void test1() {
        // 创建日志对象
        Log log = LogFactory.getLog("jcl");
        // 日志记录输出
        log.info("info");
        //org.apache.commons.logging.impl.Jdk13LumberjackLogger;
        //org.apache.commons.logging.impl.Jdk14Logger;
        //org.apache.commons.logging.impl.Log4JLogger;
        //org.apache.commons.logging.impl.SimpleLog;
    }
}
