package com.apress.prospring4.ch2._ConstructionInjection.ComplexJavaType;

public class EmployeeService {

  private Employee employee;

  EmployeeService(Employee employee) {
    this.employee = employee;
  }

  @Override
  public String toString() {
    return employee.toString();
  }

}
