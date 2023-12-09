package com.example;

import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service("asyncService")
public class AsyncServiceImpl implements AsyncService {

  final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

  @Async
  @Override
  public void asyncTask() throws InterruptedException {
    logger.info("Start execution of async. task");

    Thread.sleep(10000);

    logger.info("Complete execution of async. task");
  }

  @Async
  @Override
  public Future<String> asyncWithReturn(String name) throws InterruptedException {
    logger.info("Start execution of async. task with return for {}", name);

    Thread.sleep(5000);

    logger.info("Complete execution of async. task with return for {}", name);

    return new AsyncResult<>("Hello: " + name);
  }
}
