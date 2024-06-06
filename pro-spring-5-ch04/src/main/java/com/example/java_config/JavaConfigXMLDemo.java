package com.example.java_config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigXMLDemo {

  public static void main(String... args) {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/java-config-context.xml");
    MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
    renderer.render();
  }
}
