package org.flowable.holidayrequest.callback;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SendRejectionMail implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) {
    System.out.println("*********************************************************************************");
    System.out.println("Send Rejection Mail " + execution.getVariable("employee"));
    System.out.println("*********************************************************************************");
  }

}
