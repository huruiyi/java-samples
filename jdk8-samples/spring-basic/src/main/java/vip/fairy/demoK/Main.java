package vip.fairy.demoK;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {

    ApplicationContext context = new AnnotationConfigApplicationContext("vip.fairy.demoK");
    SequenceDao sequenceDao = context.getBean(SequenceDao.class);

    System.out.println(sequenceDao.getNextValue("IT") + sequenceDao.getNextValue("IT"));
    System.out.println(sequenceDao.getNextValue("IT") + sequenceDao.getNextValue("IT"));
    System.out.println(sequenceDao.getNextValue("IT") + sequenceDao.getNextValue("IT"));
    System.out.println(sequenceDao.getNextValue("IT") + sequenceDao.getNextValue("IT"));
  }
}
