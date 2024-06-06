package com.apress.prospring4.ch2.customScope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("springCustomScope.xml");
    MessagePrinter printer = context.getBean(MessagePrinter.class);
    printer.printMessage();
  }

}
