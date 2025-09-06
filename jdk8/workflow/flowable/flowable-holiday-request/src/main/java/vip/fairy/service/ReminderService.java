package vip.fairy.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.engine.impl.el.JuelExpression;

/**
 * 超时提醒
 */
@Data
@Slf4j
public class ReminderService implements JavaDelegate {

  private JuelExpression taskId;
  private JuelExpression taskName;
  private JuelExpression assignee;
  private JuelExpression employeeName;

  @Override
  public void execute(DelegateExecution execution) {
    log.info("taskId:{},taskName:{},assignee:{},employeeName:{}", taskId, taskName, assignee, employeeName);
    System.out.println("---------------------------------------------------------------:" + taskId);
  }
}
