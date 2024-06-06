package com.example.simple_before_after_advice;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class SimpleBeforeAfterAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

  public static void main(String... args) {
    Guitarist johnMayer = new Guitarist();

    ProxyFactory pf = new ProxyFactory();
    pf.addAdvice(new SimpleBeforeAfterAdvice());
    pf.setTarget(johnMayer);

    Guitarist proxy = (Guitarist) pf.getProxy();

    proxy.sing();
  }


  @Override
  public void before(Method method, Object[] args, Object target)
      throws Throwable {
    System.out.println("Before '" + method.getName() + "', tune guitar.");
  }


  @Override
  public void afterReturning(Object returnValue, Method method,
      Object[] args, Object target) {
    System.out.println("After '" + method.getName() + "' put down guitar.");
  }
}
