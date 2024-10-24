package com.apress.prospring4.ch2.EL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

  public static void main(String[] args) {
    @SuppressWarnings("resource")
    ApplicationContext ctx = new ClassPathXmlApplicationContext("expressionLanguageSpring.xml");

    ShopList list = (ShopList) ctx.getBean("list");
    System.out.println(list);

    list = (ShopList) ctx.getBean("list2");
    System.out.println(list);

    list = (ShopList) ctx.getBean("list3");
    System.out.println(list);

    list = (ShopList) ctx.getBean("list4");
    System.out.println(list);

    list = (ShopList) ctx.getBean("list5");
    System.out.println(list);

    list = (ShopList) ctx.getBean("list6");
    System.out.println(list);

    list = (ShopList) ctx.getBean("list7");
    System.out.println(list);

    list = (ShopList) ctx.getBean("list8");
    System.out.println(list);

    list = (ShopList) ctx.getBean("list9");
    System.out.println(list);

    list = (ShopList) ctx.getBean("list10");
    System.out.println(list);

    list = (ShopList) ctx.getBean("list11");
    System.out.println(list);

    list = (ShopList) ctx.getBean("list12");
    System.out.println(list);
  }

}
