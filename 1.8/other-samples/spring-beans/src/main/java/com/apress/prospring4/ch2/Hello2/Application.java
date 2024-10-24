package com.apress.prospring4.ch2.Hello2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
    MessagePrinter printer = context.getBean(MessagePrinter.class);
    printer.printMessage();
  }

}
