package com.apress.prospring4.ch2.TightlyCoupledWithoutSpring;


public class EmployeeServiceImp implements EmployeeService {

  public Long generateEmployeeId() {
    return System.currentTimeMillis();
  }

}
