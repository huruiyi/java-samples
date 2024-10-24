package com.apress.prospring5.ch3;

import com.apress.prospring5.ch3.renderer.MessageRenderer;
import org.springframework.context.support.GenericXmlApplicationContext;

public class LazyConfigBeans {

  public static void main(String... args) {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/app-context-lazy-xml.xml");
    ctx.refresh();
    MessageRenderer messageRenderer = ctx.getBean("renderer", MessageRenderer.class);
    messageRenderer.render();
    ctx.close();
  }

}
