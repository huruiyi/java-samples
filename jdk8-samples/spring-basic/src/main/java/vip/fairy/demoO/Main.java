package vip.fairy.demoO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vip.fairy.demoO.config.SequenceConfiguration;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(SequenceConfiguration.class);

    SequenceGenerator generator = context.getBean(SequenceGenerator.class);

    System.out.println(generator.getSequence());
    System.out.println(generator.getSequence());
  }
}