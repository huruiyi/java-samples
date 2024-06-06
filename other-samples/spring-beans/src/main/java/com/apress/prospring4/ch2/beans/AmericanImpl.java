package com.apress.prospring4.ch2.beans;

public class AmericanImpl implements Person {

  private String name;
  private int age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public void Speak() {
    System.out.println("I'am American ,My name is" + this.name + ",I'am " + this.age + "years old!");
  }

}
