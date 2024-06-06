package com.example.reflect;

public class Person {

  public String name;
  private int age;

  public Person() {

  }

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public static void say(String str) {
    System.out.println("我在说： " + str);
  }

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

  public void showInfo() {
    System.out.println("我叫" + name + "，今年" + age + "岁");
  }

  public void sayHi() {
    System.out.println("Hello World");
  }

  public void print(int i) {
    System.out.println("我在写数字： " + i);
  }

  public int add(int a, int b) {
    return a + b;
  }
}
