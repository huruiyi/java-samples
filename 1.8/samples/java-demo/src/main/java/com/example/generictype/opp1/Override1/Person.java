package com.example.generictype.opp1.Override1;


abstract class Person {

  String name;
  String surname;

  Person() {

  }

  Person(String name, String surname) {
    this.name = name;
    this.surname = surname;
  }

  abstract void getDescription();
}
