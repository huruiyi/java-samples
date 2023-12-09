package com.example.bean_aliases;

import com.example.bean_aliases.annotated.Singer;
import java.util.Arrays;
import java.util.Map;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

public class AliasConfigDemo {

  public static void main(String... args) {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AliasBeanConfig.class);
    Map<String, Singer> beans = ctx.getBeansOfType(Singer.class);
    beans.forEach((key, value) -> System.out.println("id: " + key + "\n aliases: " + Arrays.toString(ctx.getAliases(key)) + "\n"));
    ctx.close();
  }

  @Configuration
  static class AliasBeanConfig {
    @Bean(name = {"johnMayer", "john", "jonathan", "johnny"})
    public Singer singer() {
      return new Singer();
    }
  }
}
