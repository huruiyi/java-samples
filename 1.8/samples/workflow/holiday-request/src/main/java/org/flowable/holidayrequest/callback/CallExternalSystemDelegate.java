package org.flowable.holidayrequest.callback;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class CallExternalSystemDelegate implements JavaDelegate {

  public void execute(DelegateExecution execution) {
    System.out.println("*********************************************************************************");
    System.out.println("Calling the external system for employee " + execution.getVariable("employee"));
    System.out.println("*********************************************************************************");
  }

}
