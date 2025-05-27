package com.example.profiles;

import com.example.profiles.config.HighschoolConfig;
import com.example.profiles.config.KindergartenConfig;
import com.example.profiles.service.FoodProviderService;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class ProfileJavaConfigExample {

  public static void main(String... args) {
    //测Ok,必须设置虚拟机选项
    //虚拟机选项：-Dspring.profiles.active=hig  hschool
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(KindergartenConfig.class, HighschoolConfig.class);
    ctx.getEnvironment().setActiveProfiles("kindergarten");
    FoodProviderService foodProviderService = ctx.getBean("foodProviderService", FoodProviderService.class);
    List<Food> lunchSet = foodProviderService.provideLunchSet();
    for (Food food : lunchSet) {
      System.out.println("Food: " + food.getName());
    }
    ctx.close();
  }
}

