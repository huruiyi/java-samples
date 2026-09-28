package com.example.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibMethodInterceptor implements MethodInterceptor {

  @Override
  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
    System.out.println("[CGLIB] 方法调用前: " + method.getName());
    Object result = proxy.invokeSuper(obj, args);
    System.out.println("[CGLIB] 方法调用后");
    return result;
  }

}
