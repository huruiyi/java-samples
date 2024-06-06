package com.example.reflect;

public class Employee {

  private String name;

  public Employee(String name) {
    this.name = name;
  }

  public String toString() {
    return "姓名： " + this.name;
  }
}
