package com.apress.prospring5.ch3.annotated;

import com.apress.prospring5.ch3.renderer.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldSpringAnnotated {

  public static void main(String... args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
    MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
    mr.render();
  }
}
