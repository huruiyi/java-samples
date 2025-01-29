package com.example.demoL;

import com.example.demoL.config.ConfigurationL;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

  public static void main(String[] args) {

    ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationL.class);

    SequenceGenerator generator = context.getBean(SequenceGenerator.class);

    System.out.println(generator.getSequence());
    System.out.println(generator.getSequence());
  }
}
