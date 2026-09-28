package com.example.spring;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  @Pointcut("execution(* com.example.service.HelloPrint.printStr(..))")
  private void printStrPointcut() {
  }


  //@Before("execution(* com.example.service.HelloPrint.printStr(..))")
  @Before("printStrPointcut()")
  public void logBefore(JoinPoint joinPoint) {
    System.out.println("[AOP] 调用开始: " + joinPoint.getSignature());
  }


  @Before("printStrPointcut()")
  public void logBeforeWithArgs(JoinPoint joinPoint) {
    System.out.println("[AOP] 调用: " + joinPoint.getSignature());
    if (joinPoint.getArgs().length > 0) {
      System.out.println("[AOP] 参数列表: " + Arrays.toString(joinPoint.getArgs()));
    }
  }

  //@Around("execution(* com.example.service.HelloPrint.printStr(..))")
  @Around("printStrPointcut()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    System.out.println("[AOP] 执行开始: " + joinPoint.getSignature());

    try {
      Object result = joinPoint.proceed();
      System.out.println("[AOP] 执行结束: " + joinPoint.getSignature() + ", 耗时: " + (System.currentTimeMillis() - startTime) + "ms");
      return result;
    } catch (Exception e) {
      System.out.println("[AOP] 执行异常: " + joinPoint.getSignature() + ", 异常: " + e.getMessage());
      throw e;
    }
  }

  //@AfterThrowing(pointcut = "execution(* com.example.service.HelloPrint.printStr(..))", throwing = "ex")
  @AfterThrowing(pointcut = "printStrPointcut()", throwing = "ex")
  public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
    System.out.println("[AOP] 方法抛出异常: " + joinPoint.getSignature() + ", 异常信息: " + ex.getMessage());
  }

  //@AfterReturning(pointcut = "execution(* com.example.service.HelloPrint.printStr(..))", returning = "result")
  @AfterReturning(pointcut = "printStrPointcut()", returning = "result")
  public void logAfterReturning(JoinPoint joinPoint, Object result) {
    System.out.println("[AOP] 方法返回值: " + joinPoint.getSignature() + ", 返回结果: " + result);
  }

  //@After("execution(* com.example.service.HelloPrint.printStr(..))")
  @After("printStrPointcut()")
  public void logAfter(JoinPoint joinPoint) {
    System.out.println("[AOP] 调用结束: " + joinPoint.getSignature());
  }

}
