package com.apress.prospring4.ch2.SpringBeanLifeCycle.callbacks;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

  public static void main(String[] args) {

    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("SpringBeanLifeCycle.xml");

    EmployeeService employeeService = (EmployeeService) context.getBean("employeeServiceBean");

    System.out.println(employeeService.generateEmployeeId());

    context.close();

  }

}
