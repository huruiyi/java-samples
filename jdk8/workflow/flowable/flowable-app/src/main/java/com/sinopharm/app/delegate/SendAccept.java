package com.sinopharm.app.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SendAccept implements JavaDelegate {

  @Override
  public void execute(DelegateExecution delegateExecution) {
    System.out.println("send accept");
  }

}
