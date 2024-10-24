package com.apress.prospring4.ch2._SetterBasedDependencyInjection.ComplexJavaType;

public class EmployeeService {

  private Employee employee;

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

}
