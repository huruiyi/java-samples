package com.example.proxyfactorybean_introduction;

import com.example.model.Contact;
import com.example.model.introduction.IsModified;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class IntroductionConfigDemo {


  private static GenericApplicationContext getContextV1() {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/app-context-xml-05.xml");
    ctx.refresh();
    return ctx;
  }


  private static GenericApplicationContext getContextV2() {
    return new AnnotationConfigApplicationContext(AppConfig.class);
  }

  public static void main(String... args) {
    GenericApplicationContext ctx = getContextV2();

    Contact bean = (Contact) ctx.getBean("bean");
    IsModified mod = (IsModified) bean;

    System.out.println("Is Contact?: " + (bean instanceof Contact));
    System.out.println("Is IsModified?: " + (bean instanceof IsModified));

    System.out.println("Has been modified?: " + mod.isModified());
    bean.setName("John Mayer");

    System.out.println("Has been modified?: " + mod.isModified());
    bean.setName("Eric Clapton");

    System.out.println("Has been modified?: " + mod.isModified());
  }
}
