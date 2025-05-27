package vip.fairy.demoM;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext("vip.fairy.demoM");

    SequenceService sequenceService = context.getBean(SequenceService.class);

    System.out.println(sequenceService.generate("IT"));
    System.out.println(sequenceService.generate("IT"));
  }
}
