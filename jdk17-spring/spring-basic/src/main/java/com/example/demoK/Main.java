package com.example.demoK;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext("com.example.demoK");
    SequenceDao sequenceDao = context.getBean(SequenceDao.class);

    System.out.println(sequenceDao.getNextValue("IT") + sequenceDao.getNextValue("IT"));
    System.out.println(sequenceDao.getNextValue("IT") + sequenceDao.getNextValue("IT"));
    System.out.println(sequenceDao.getNextValue("IT") + sequenceDao.getNextValue("IT"));
    System.out.println(sequenceDao.getNextValue("IT") + sequenceDao.getNextValue("IT"));
  }
}
