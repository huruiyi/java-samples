package org.example.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class LeaveTaskListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) {
    System.out.printf("executionId: %s\n", execution.getId());
  }

}
