package com.example.simple_types.annotated;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("injectSimpleSpel")
public class InjectSimpleSpelDemo {

  @Value("#{injectSimpleConfig.name}")
  private String name;

  @Value("#{injectSimpleConfig.age + 1}")
  private int age;

  @Value("#{injectSimpleConfig.height}")
  private float height;

  @Value("#{injectSimpleConfig.programmer}")
  private boolean programmer;

  @Value("#{injectSimpleConfig.ageInSeconds}")
  private Long ageInSeconds;

  public static void main(String... args) {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/simple-types-app-context-annotation.xml");
    ctx.refresh();

    InjectSimpleSpelDemo simple = (InjectSimpleSpelDemo) ctx.getBean("injectSimpleSpel");
    System.out.println(simple);

    ctx.close();
  }

  public String toString() {
    return "Name: " + name + "\n"
        + "Age: " + age + "\n"
        + "Age in Seconds: " + ageInSeconds + "\n"
        + "Height: " + height + "\n"
        + "Is Programmer?: " + programmer;
  }
}
