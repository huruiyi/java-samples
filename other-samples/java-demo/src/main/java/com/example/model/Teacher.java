package com.example.model;

public class Teacher implements Comparable {

  Integer Id;
  String Name;
  Integer Age;


  public Teacher(Integer id, String name, int age) {
    Id = id;
    Name = name;
    Age = age;
  }

  public Integer getId() {
    return Id;
  }

  public void setId(Integer id) {
    Id = id;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public Integer getAge() {
    return Age;
  }

  public void setAge(Integer age) {
    Age = age;
  }

  @Override
  public String toString() {
    return "Person{" + "Id=" + Id + ", Name='" + Name + ", Age=" + Age + '}';
  }

  public int compareTo(Object o) {
    Teacher teacher = (Teacher) o;
    return this.getName().compareTo(teacher.getName());
  }
}
