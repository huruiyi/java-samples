package com.example.bean_aliases.xml;

import java.util.Map;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanNamingTest {

  public static void main(String[] args) {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/bean-aliases-app-context-01.xml");
    ctx.refresh();

    Map<String, String> beans = ctx.getBeansOfType(String.class);

    beans.forEach((key, value) -> System.out.println(key));

    ctx.close();
  }

}
