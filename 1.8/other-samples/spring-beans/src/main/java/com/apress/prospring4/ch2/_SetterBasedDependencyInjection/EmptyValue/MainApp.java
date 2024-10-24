package com.apress.prospring4.ch2._SetterBasedDependencyInjection.EmptyValue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainApp {

  public static void main(String[] args) {

    ApplicationContext context = new ClassPathXmlApplicationContext("SetterBasedDependencyInjectionEmptyValue.xml");

    Employee employeeService = (Employee) context.getBean("employeeBean");
    System.out.println(employeeService.getEmployeeName());

  }

}
