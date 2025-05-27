package com.apress.prospring4.ch2.LooseCouplingWithSpring;


public class EmployeeServiceImp implements EmployeeService {

  public Long generateEmployeeId() {

    return System.currentTimeMillis();

  }

}
