package com.apress.prospring4.ch2._SetterBasedDependencyInjection.v1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("springDI.xml");
    MessagePrinter printer = context.getBean(MessagePrinter.class);
    printer.printMessage();
  }

}
