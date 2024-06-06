package com.example;

import com.example.service.RateLimitClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RateLimitClientTest {

  public static final Logger LOGGER = LoggerFactory.getLogger(RateLimitClientTest.class);


  @Autowired
  public RateLimitClient rateLimitClient;

  @Test
  public void redisLuaScriptTest() throws Exception {
    Random random = new Random();
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

    for (int i = 0; i < 100; i++) {
      // 令牌桶的线程安全验证
      executor.execute(() -> {
        int requireToken = random.nextInt(1000);
        LOGGER.info("requireToken:{}", requireToken);

        List<Long> intervalAndToken = rateLimitClient.acquireIntervalAndToken("RATE_1000", requireToken);
        LOGGER.info("requireToken:{}, acquireToken:{} ", requireToken, intervalAndToken);
      });
    }
  }


}
