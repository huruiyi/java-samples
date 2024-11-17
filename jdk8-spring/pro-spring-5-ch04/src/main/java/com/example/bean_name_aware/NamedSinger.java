package com.example.bean_name_aware;

import org.springframework.beans.factory.BeanNameAware;

public class NamedSinger implements BeanNameAware {

  private String name;

  /**
   *  {@link BeanNameAware#setBeanName(String)}
   */
  public void setBeanName(String beanName) {
    this.name = beanName;
  }

  public void sing() {
    System.out.println("Singer [" + name + "] - sing()");
  }
}
