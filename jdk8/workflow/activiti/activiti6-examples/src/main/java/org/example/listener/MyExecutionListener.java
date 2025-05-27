package org.example.listener;


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class MyExecutionListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) {
    String eventName = execution.getEventName();
    switch (eventName) {
      case EVENTNAME_START:
        start(execution);
        break;
      case EVENTNAME_END:
        end(execution);
        break;
      case EVENTNAME_TAKE:
        take(execution);
        break;
      default:
        break;
    }
  }

  public void start(DelegateExecution execution) {
    System.out.println("============executionListener start============");
    System.out.println("事件名称:" + execution.getEventName());
    System.out.println("ActivitiId:" + execution.getCurrentActivityId());
    System.out.println("============executionListener  end============");
  }

  public void end(DelegateExecution execution) {
    System.out.println("============executionListener start============");
    System.out.println("事件名称:" + execution.getEventName());
    System.out.println("ActivitiId:" + execution.getCurrentActivityId());
    System.out.println("============executionListener  end============");
  }

  public void take(DelegateExecution execution) {
    System.out.println("============executionListener start============");
    System.out.println("事件名称:" + execution.getEventName());
    System.out.println("ActivitiId:" + execution.getCurrentActivityId());
    System.out.println("============executionListener  end============");
  }

}
