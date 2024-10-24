package com.example.proxyfactorybean;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class AuditAdvice implements MethodBeforeAdvice {

  @Override
  public void before(Method method, Object[] args, Object target)
      throws Throwable {
    System.out.println("Executing: " + method);
  }
}
