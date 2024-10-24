package com.example.bean_autowiring.annotated;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component("johnMayer")
@DependsOn("gopher")
public class Singer implements ApplicationContextAware {

  ApplicationContext applicationContext;
  private Guitar guitar;

  public Singer() {
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  public void sing() {
    guitar = applicationContext.getBean("gopher", Guitar.class);
    guitar.sing();
  }
}
