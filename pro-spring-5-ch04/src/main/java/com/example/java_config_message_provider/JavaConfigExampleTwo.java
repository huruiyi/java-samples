package com.example.java_config_message_provider;

import com.example.java_config_message_provider.mixed.AppConfigFive;
import com.example.java_config_message_provider.model.MessageRenderer;
import com.example.java_config_message_provider.multiple.AppConfigThree;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigExampleTwo {

  public static void main(String... args) {
    demo1();
    demo2();
    demo3();
  }

  static void demo1() {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigTwo.class);
    MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
    renderer.render();
  }

  static void demo2() {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigThree.class);
    MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
    renderer.render();
  }

  static void demo3() {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigFive.class);
    MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
    renderer.render();
  }
}
