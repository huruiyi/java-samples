package org.example.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MyTaskListener implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {
    //"创建申请".equals(delegateTask.getName())
    if (EVENTNAME_CREATE.equals(delegateTask.getEventName())) {
      delegateTask.setAssignee("张无忌");
      delegateTask.setCategory("fuck");
    }

    String eventName = delegateTask.getEventName();
    switch (eventName) {
      case EVENTNAME_CREATE:
        create(delegateTask);
        break;
      case EVENTNAME_ASSIGNMENT:
        assigment(delegateTask);
        break;
      case EVENTNAME_COMPLETE:
        complete(delegateTask);
        break;
      case EVENTNAME_DELETE:
        delete(delegateTask);
        break;
      default:
        break;
    }
  }

  public void create(DelegateTask delegateTask) {
    System.out.println("******************TaskListener start******************");
    String taskDefinitionKey = delegateTask.getTaskDefinitionKey();
    String eventName = delegateTask.getEventName();
    System.out.println("事件名称:" + eventName);
    System.out.println("taskDefinitionKey:" + taskDefinitionKey);
    System.out.println("******************TaskListener end******************");
  }

  public void assigment(DelegateTask delegateTask) {
    System.out.println("******************TaskListener start******************");
    String taskDefinitionKey = delegateTask.getTaskDefinitionKey();
    String eventName = delegateTask.getEventName();
    System.out.println("事件名称:" + eventName);
    System.out.println("taskDefinitionKey:" + taskDefinitionKey);
    System.out.println("******************TaskListener end******************");
  }

  public void complete(DelegateTask delegateTask) {
    System.out.println("******************TaskListener start******************");
    String taskDefinitionKey = delegateTask.getTaskDefinitionKey();
    String eventName = delegateTask.getEventName();
    System.out.println("事件名称:" + eventName);
    System.out.println("taskDefinitionKey:" + taskDefinitionKey);
    System.out.println("******************TaskListener end******************");
  }


  public void delete(DelegateTask delegateTask) {
    System.out.println("******************TaskListener start******************");
    String taskDefinitionKey = delegateTask.getTaskDefinitionKey();
    String eventName = delegateTask.getEventName();
    System.out.println("事件名称:" + eventName);
    System.out.println("taskDefinitionKey:" + taskDefinitionKey);
    System.out.println("******************TaskListener end******************");
  }
}
