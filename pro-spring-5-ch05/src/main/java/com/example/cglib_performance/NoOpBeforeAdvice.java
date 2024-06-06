package com.example.cglib_performance;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class NoOpBeforeAdvice implements MethodBeforeAdvice {

  @Override
  public void before(Method method, Object[] args, Object target)
      throws Throwable {
    // no-op
  }
}
