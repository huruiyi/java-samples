package vip.fairy.demoJ;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vip.fairy.demoJ.config.SequenceGeneratorConfiguration;


public class App {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(SequenceGeneratorConfiguration.class);

    SequenceGenerator generator = context.getBean(SequenceGenerator.class);

    System.out.println(generator.getSequence());
    System.out.println(generator.getSequence());
    System.out.println(generator.getSequence());
    System.out.println(generator.getSequence());
  }
}
