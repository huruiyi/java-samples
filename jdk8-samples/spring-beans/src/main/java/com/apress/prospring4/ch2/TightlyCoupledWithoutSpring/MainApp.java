package com.apress.prospring4.ch2.TightlyCoupledWithoutSpring;

public class MainApp {

  public static void main(String[] args) {
    EmployeeService employeeService = new EmployeeServiceImp();
    System.out.println(employeeService.generateEmployeeId());
  }

}
