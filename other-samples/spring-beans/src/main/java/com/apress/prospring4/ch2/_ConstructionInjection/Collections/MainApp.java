package com.apress.prospring4.ch2._ConstructionInjection.Collections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

  public static void main(String[] args) {

    ApplicationContext context = new ClassPathXmlApplicationContext("InjectionForCollections.xml");

    EmployeeService employeeService = (EmployeeService) context.getBean("employeeServiceBean");

    System.out.println("==========List========");
    System.out.println(employeeService.getLists());
    System.out.println("=====Set========");
    System.out.println(employeeService.getSets());
    System.out.println("=====Map========");
    System.out.println(employeeService.getMaps());
  }

}
