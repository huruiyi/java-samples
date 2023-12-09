package com.example.bean_autowiring.sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

@Lazy
@Component("gigi")
public class TrickyTargetDemo {

  Foo fooOne;
  Foo fooTwo;
  Bar bar;

  public TrickyTargetDemo() {
    System.out.println("Target.constructor()");
  }

  public TrickyTargetDemo(Foo fooOne) {
    System.out.println("Target(Foo) called");
  }

  public TrickyTargetDemo(Foo fooOne, Bar bar) {
    System.out.println("Target(Foo, Bar) called");
  }

  public static void main(String... args) {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/bean-autowiring-app-context-05.xml");
    ctx.refresh();
    TrickyTargetDemo t = ctx.getBean(TrickyTargetDemo.class);
    ctx.close();
  }

  // comment @Qualifier annotation to cause NoUniqueBeanDefinitionException being thrown at runtime
  @Autowired
  @Qualifier("fooImplOne")
  public void setFooOne(Foo fooOne) {
    this.fooOne = fooOne;
    System.out.println("Property fooOne set");
  }

  // comment @Qualifier annotation to cause NoUniqueBeanDefinitionException being thrown at runtime
  // and make sure for @Primary in FooImpl to be commented as well
  @Autowired
  @Qualifier("fooImplTwo")
  public void setFooTwo(Foo foo) {
    this.fooTwo = foo;
    System.out.println("Property fooTwo set");
  }

  @Autowired
  public void setBar(Bar bar) {
    this.bar = bar;
    System.out.println("Property bar set");
  }
}
