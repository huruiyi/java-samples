package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class TaskToExecute {

  private final TaskExecutor taskExecutor;

  public TaskToExecute(TaskExecutor taskExecutor) {
    this.taskExecutor = taskExecutor;
  }

  private final Logger logger = LoggerFactory.getLogger(TaskToExecute.class);

  public void executeTask() {
    for (int i = 0; i < 9; ++i) {
      taskExecutor.execute(() -> logger.info("Hello from thread: {}", Thread.currentThread().getName()));
    }
  }
}
