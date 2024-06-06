package com.apress.prospring4.ch2._ConstructionInjection.SimpleJavaType;

public class EmployeeService {

  private String greeting;

  EmployeeService(String message) {
    this.greeting = message;
  }

  @Override
  public String toString() {
    return greeting;
  }

}
