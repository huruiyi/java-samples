package com.apress.prospring4.ch2.Ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class App {

  public static void main(String[] args) {

    ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
    MessagePrinter printer = context.getBean(MessagePrinter.class);
    printer.printMessage();
  }

}
