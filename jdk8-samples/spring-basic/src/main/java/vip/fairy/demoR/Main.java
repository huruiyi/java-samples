package vip.fairy.demoR;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vip.fairy.demoR.config.SequenceConfiguration;


public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(SequenceConfiguration.class);
    context.refresh();

    SequenceGenerator generator = (SequenceGenerator) context.getBean("sequenceGenerator");

    System.out.println(generator.getSequence());
    System.out.println(generator.getSequence());
  }
}
