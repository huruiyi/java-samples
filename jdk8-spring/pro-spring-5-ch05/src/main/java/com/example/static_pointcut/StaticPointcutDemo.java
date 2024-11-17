package com.example.static_pointcut;

import com.example.model.Singer;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class StaticPointcutDemo {

  public static void main(String... args) {

    Pointcut pc = new SimpleStaticPointcut();
    Advice advice = new SimpleAdvice();
    Advisor advisor = new DefaultPointcutAdvisor(pc, advice);

    System.out.println("*************************************************************");

    ProxyFactory pf = new ProxyFactory();
    pf.addAdvisor(advisor);

    GoodGuitarist johnMayer = new GoodGuitarist();
    pf.setTarget(johnMayer);

    Singer proxyOne = (Singer) pf.getProxy();
    proxyOne.sing();

    System.out.println("*************************************************************");

    pf = new ProxyFactory();
    pf.addAdvisor(advisor);

    GreatGuitarist ericClapton = new GreatGuitarist();
    pf.setTarget(ericClapton);
    Singer proxyTwo = (Singer) pf.getProxy();
    proxyTwo.sing();
  }
}
