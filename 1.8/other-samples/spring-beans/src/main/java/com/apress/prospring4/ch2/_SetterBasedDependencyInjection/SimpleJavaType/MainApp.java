package com.apress.prospring4.ch2._SetterBasedDependencyInjection.SimpleJavaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainApp {

  public static void main(String[] args) {

    ApplicationContext context = new ClassPathXmlApplicationContext("SetterBasedDependencyInjectionSimpleJavaType.xml");

    Employee employeeService = (Employee) context.getBean("employeeBean");
    System.out.println(employeeService.getEmployeeName());

  }

}
