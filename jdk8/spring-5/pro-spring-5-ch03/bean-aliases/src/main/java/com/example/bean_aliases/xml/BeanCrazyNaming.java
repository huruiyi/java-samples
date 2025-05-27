package com.example.bean_aliases.xml;

import java.util.Arrays;
import java.util.Map;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanCrazyNaming {

  public static void main(String... args) {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/bean-aliases-app-context-03.xml");
    ctx.refresh();
    Map<String, String> beans = ctx.getBeansOfType(String.class);
    beans.forEach((key, value) -> System.out.println("id: " + key + "\n aliases: " + Arrays.toString(ctx.getAliases(key)) + "\n"));
    ctx.close();
  }
}
