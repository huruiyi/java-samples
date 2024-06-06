package com.apress.prospring4.ch2._Autowiring.ByConstructor;

public class EmployeeService {

  private Employee employee;

  public EmployeeService(Employee employee) {
    this.employee = employee;
  }

  public Employee getEmployee() {
    return employee;
  }

}
