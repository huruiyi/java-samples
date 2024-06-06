package com.example.generictype.opp1.Override2;

class Student extends Person {

  public Student(String name, String surname) {
    super(name, surname);
  }

  @Override
  void getDescription() {
    System.out.println("I'm student");
    System.out.println("Name: " + name);
    System.out.println("Surname: " + surname);
  }

}
