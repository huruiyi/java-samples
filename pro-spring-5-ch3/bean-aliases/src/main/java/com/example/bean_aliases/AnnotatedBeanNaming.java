package com.example.bean_aliases;

import com.example.bean_aliases.annotated.Singer;
import java.util.Arrays;
import java.util.Map;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AnnotatedBeanNaming {

  public static void main(String... args) {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/bean-aliases-app-context-annotated.xml");
    ctx.refresh();

    Map<String, Singer> beans = ctx.getBeansOfType(Singer.class);
    beans.forEach((key, value) -> System.out.println("id: " + key + "\n aliases: " + Arrays.toString(ctx.getAliases(key)) + "\n"));

    ctx.close();
  }
}
