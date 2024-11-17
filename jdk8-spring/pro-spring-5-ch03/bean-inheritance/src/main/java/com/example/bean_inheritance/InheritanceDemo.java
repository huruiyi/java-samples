package com.example.bean_inheritance;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InheritanceDemo {

  public static void main(String... args) {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/bean-inheritance-context.xml");
    ctx.refresh();

    Singer parent = (Singer) ctx.getBean("parent");
    Singer child = (Singer) ctx.getBean("child");

    System.out.println("Parent:\n" + parent);
    System.out.println("Child:\n" + child);
  }
}
