package vip.fairy.demoL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vip.fairy.demoL.config.SequenceConfiguration;


public class App {

  public static void main(String[] args) {

    ApplicationContext context = new AnnotationConfigApplicationContext(SequenceConfiguration.class);

    SequenceGenerator generator = context.getBean(SequenceGenerator.class);

    System.out.println(generator.getSequence());
    System.out.println(generator.getSequence());
  }
}
