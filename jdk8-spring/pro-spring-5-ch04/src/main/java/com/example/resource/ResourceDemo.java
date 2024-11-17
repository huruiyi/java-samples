package com.example.resource;

import java.io.File;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class ResourceDemo {

  public static void main(String... args) throws Exception {
    ApplicationContext ctx = new ClassPathXmlApplicationContext();

    File file = File.createTempFile("test", "txt");

    Resource res1 = ctx.getResource("file://" + file.getPath());
    System.out.println(res1.exists());

    Resource res2 = ctx.getResource("classpath:test.txt");
    displayInfo(res2);

    Resource res3 = ctx.getResource("https://www.baidu.com");
    displayInfo(res3);
  }

  private static void displayInfo(Resource res) throws Exception {
    System.out.println(res.getClass());
    System.out.println(res.getURL().getContent());
  }
}
