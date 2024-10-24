package com.example.aop_hello_world;

import org.springframework.aop.framework.ProxyFactory;

public class AgentAOPDemo {

  public static void main(String... args) {
    Agent target = new Agent();

    ProxyFactory pf = new ProxyFactory();
    pf.addAdvice(new AgentDecorator());
    pf.setTarget(target);

    Agent proxy = (Agent) pf.getProxy();

    target.speak();
    System.out.println("---------------------------------------------------------------");
    proxy.speak();
  }

}
