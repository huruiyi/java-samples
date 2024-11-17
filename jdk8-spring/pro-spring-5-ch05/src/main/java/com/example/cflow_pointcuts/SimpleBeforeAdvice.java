package com.example.cflow_pointcuts;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class SimpleBeforeAdvice implements MethodBeforeAdvice {

  @Override
  public void before(Method method, Object[] args, Object target) {
    System.out.println("Before method: " + method);
  }

}
