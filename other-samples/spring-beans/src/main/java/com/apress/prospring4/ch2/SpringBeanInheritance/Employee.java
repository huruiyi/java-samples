package com.apress.prospring4.ch2.SpringBeanInheritance;

public class Employee {

  private int employeeId;
  private String employeeName;
  private String country;

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "Employee ID: " + employeeId + " Name: " + employeeName + " Country: " + country;
  }
}
