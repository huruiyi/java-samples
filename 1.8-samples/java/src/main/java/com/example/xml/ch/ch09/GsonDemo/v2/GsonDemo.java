package com.example.xml.ch.ch09.GsonDemo.v2;

import static java.lang.System.out;

import com.google.gson.Gson;

public class GsonDemo {

  public static void main(String[] args) {
    Gson gson = new Gson();
    String json = "{ name: \"John Doe\", age: 45 }";
    Person person = gson.fromJson(json, Person.class);
    out.println(person);
  }

  static class Person {

    String name;
    int age;

    Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public String toString() {
      return name + ": " + age;
    }
  }
}
