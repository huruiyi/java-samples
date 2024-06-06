package com.example.simple_around_advice;

import org.springframework.aop.framework.ProxyFactory;

public class ProfilingDemo {

  public static void main(String... args) {
    WorkerBean bean = getWorkerBean();
    bean.doSomeWork(10000000);
  }

  private static WorkerBean getWorkerBean() {
    WorkerBean target = new WorkerBean();

    ProxyFactory factory = new ProxyFactory();
    factory.setTarget(target);
    factory.addAdvice(new ProfilingInterceptor());

    return (WorkerBean) factory.getProxy();
  }
}
