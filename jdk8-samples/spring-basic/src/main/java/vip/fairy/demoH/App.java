package vip.fairy.demoH;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
    HelloWorld helloWorld1 = context.getBean(HelloWorld.class);
    helloWorld1.hello();

    HelloWorld helloWorld2 = (HelloWorld) context.getBean("helloWorld");
    helloWorld2.hello();
  }
}
