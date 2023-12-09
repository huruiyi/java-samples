package com.example.bean_autowiring.test;

import com.example.bean_autowiring.sandbox.Bar;
import com.example.bean_autowiring.sandbox.Foo;
import com.example.bean_autowiring.sandbox.FooImplOne;
import com.example.bean_autowiring.sandbox.FooImplTwo;
import com.example.bean_autowiring.sandbox.TrickyTargetDemo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

public class TargetDemo {

  public static void main(String[] args) {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(TargetConfig.class);
    TrickyTargetDemo t = ctx.getBean(TrickyTargetDemo.class);
    ctx.close();
  }

  @Configuration
  static class TargetConfig {

    @Bean
    public Foo fooImplOne() {
      return new FooImplOne();
    }

    @Bean
    public Foo fooImplTwo() {
      return new FooImplTwo();
    }

    @Bean
    public Bar bar() {
      return new Bar();
    }

    @Bean
    public TrickyTargetDemo trickyTarget() {
      return new TrickyTargetDemo();
    }
  }
}
