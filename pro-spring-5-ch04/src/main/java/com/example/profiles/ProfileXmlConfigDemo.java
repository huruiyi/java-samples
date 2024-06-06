package com.example.profiles;

import com.example.profiles.service.FoodProviderService;
import java.util.List;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ProfileXmlConfigDemo {

  public static void main(String... args) {
    //测Ok
    //虚拟机选项：-Dspring.profiles.active=highschool
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.getEnvironment().setActiveProfiles("kindergarten");
    ctx.load("classpath:spring/profiles/*-config.xml");
    ctx.refresh();

    FoodProviderService foodProviderService = ctx.getBean("foodProviderService", FoodProviderService.class);

    List<Food> lunchSet = foodProviderService.provideLunchSet();

    for (Food food : lunchSet) {
      System.out.println("Food: " + food.getName());
    }

    ctx.close();
  }
}
