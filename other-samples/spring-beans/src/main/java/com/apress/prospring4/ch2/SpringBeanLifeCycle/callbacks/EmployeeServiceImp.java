package com.apress.prospring4.ch2.SpringBeanLifeCycle.callbacks;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class EmployeeServiceImp implements EmployeeService, InitializingBean, DisposableBean {

  public Long generateEmployeeId() {
    return System.currentTimeMillis();
  }

  public void destroy() throws Exception {

    System.out.println("Employee destroy... ");

  }

  public void afterPropertiesSet() throws Exception {

    System.out.println("Employee afterPropertiesSet... ");

  }

}
