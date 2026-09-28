package com.example.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingInvocationHandler implements InvocationHandler {

  private final Object target;

  public LoggingInvocationHandler(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("[LOG] 调用方法: " + method.getName() + ", 参数: " + java.util.Arrays.toString(args));

    long start = System.currentTimeMillis();
    Object result = method.invoke(target, args); // 调用真实对象的方法
    long end = System.currentTimeMillis();

    System.out.println("[LOG] 方法执行耗时: " + (end - start) + " ms");
    return result;
  }

}
