package com.example.aspectj_annotations;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AppTest {

  @Test
  public void configTest() {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
    documentarist.execute();
    ctx.close();
  }

  @Test
  public void xmlTest() {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/app-context-xml-00.xml");
    ctx.refresh();

    NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
    documentarist.execute();
    ctx.close();
  }

}
