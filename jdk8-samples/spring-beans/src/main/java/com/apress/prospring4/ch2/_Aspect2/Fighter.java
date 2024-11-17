package com.apress.prospring4.ch2._Aspect2;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Fighter {

  @Pointcut("execution(* com.apress.prospring4.ch2._Aspect2.Tiger.walk())")
  public void foundTiger() {
  }

  @Before(value = "foundTiger()")
  public void foundBefore() {
    System.out.println("foundBefore...");
  }

  @AfterReturning("foundTiger()")
  public void foundAfter() {
    System.out.println("foundAfter...");
  }

}
