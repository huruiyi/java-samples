package com.example.generictype.oopc;

class Employee implements nameOfInterface, someOtherInterface {

  private double salary;

  public Employee(double salary) {
    this.salary = salary;
  }

  @Override
  public void sample() {

  }

  double getSalary() {
    System.out.println(this.salary);
    return this.salary;
  }
}
