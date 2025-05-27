package vip.fairy.demoQ;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

  public static void main(String[] args) {
    ApplicationContext context = new GenericXmlApplicationContext("applicationContextQ.xml");

    SequenceGenerator generator = (SequenceGenerator) context.getBean("sequenceGenerator");

    System.out.println(generator.getSequence());
    System.out.println(generator.getSequence());
  }
}
