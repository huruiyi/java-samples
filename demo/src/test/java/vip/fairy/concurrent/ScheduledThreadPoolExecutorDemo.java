package vip.fairy.concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2, new ThreadPoolExecutor.CallerRunsPolicy());

        log.info("提交延迟任务");
        executor.schedule(() -> log.info("执行延迟任务"), 5, TimeUnit.SECONDS);
        log.info("ok.....");
    }

}
