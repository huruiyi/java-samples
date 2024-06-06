/**
 * Welcome to https://waylau.com
 */
package com.apress.prospring4.ch2._Aspect2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {

  public static void main(String[] args) {

    ApplicationContext context = new ClassPathXmlApplicationContext("_Aspect2.xml");
    Tiger tiger = context.getBean(Tiger.class);
    tiger.walk();
  }

}
