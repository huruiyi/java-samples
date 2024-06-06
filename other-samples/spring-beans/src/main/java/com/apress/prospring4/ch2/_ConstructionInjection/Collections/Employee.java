package com.apress.prospring4.ch2._ConstructionInjection.Collections;

public class Employee {

  String employeeName;

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  @Override
  public String toString() {
    return "EmployeeName: " + employeeName;
  }

}
