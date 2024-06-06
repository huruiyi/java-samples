package com.example.jsr330;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Jsr330Demo {

  public static void main(String... args) {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/jsr330-context-annotation.xml");
    ctx.refresh();

    MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
    renderer.render();

    ctx.close();
  }
}
