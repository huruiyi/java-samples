package com.apress.prospring4.ch2._Aspect3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("_Aspect3.xml");
    Tiger tiger = context.getBean(Tiger.class);
    tiger.walk();
  }

}
