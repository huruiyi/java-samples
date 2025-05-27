package vip.fairy;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SendRejectionMail implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) {
    System.out.println("Calling SendRejectionMail.... for employee " + execution.getVariable("employee"));
  }

}
