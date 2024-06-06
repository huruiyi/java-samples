package com.example.generictype.oopc;

public class App {

  public static void main(String[] args) {
    nameOfInterface a = new Employee(1000);
    ((Employee) a).getSalary();

    nameOfInterface b = new Programmer(2000);
    ((Employee) b).getSalary();

  }
}
