package com.apress.prospring4.ch2.SpringBeanLifeCycle.callbacks.xml;


public class NewEmployeeServiceImp implements EmployeeService {

  public Long generateEmployeeId() {

    return System.currentTimeMillis();

  }

  public void cleanUp() {

    System.out.println("New Employee Cleanup... ");

  }

  public void myInit() {

    System.out.println("New Employee My Init... ");

  }

}
