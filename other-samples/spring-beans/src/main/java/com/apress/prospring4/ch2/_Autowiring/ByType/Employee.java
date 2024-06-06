package com.apress.prospring4.ch2._Autowiring.ByType;

public class Employee {

  private int employeeId;

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  @Override
  public String toString() {
    return "employee id: " + employeeId;
  }
}
