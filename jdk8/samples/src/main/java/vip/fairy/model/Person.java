package vip.fairy.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

public class Person implements Comparable<Person> {

  Integer Id;
  String Name;
  Integer Age;

  @JSONField(format = "yyyy-mm-dd")
  Date BirthDay;

  public Person() {
  }

  public Person(Integer id, String name, int age) {
    Id = id;
    Name = name;
    Age = age;
  }

  public Person(Integer id, String name, Integer age, Date birthDay) {
    Id = id;
    Name = name;
    Age = age;
    BirthDay = birthDay;
  }

  public Date getBirthDay() {
    return BirthDay;
  }

  public void setBirthDay(Date birthDay) {
    BirthDay = birthDay;
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

  /*
  按照姓名排序
   */
  public int compareTo(Person person) {
    return this.getAge().compareTo(person.getAge());
  }

  @Override
  public String toString() {
    return "Person{" +
        "Id=" + Id +
        ", Name='" + Name + '\'' +
        ", Age=" + Age +
        ", BirthDay=" + BirthDay +
        '}';
  }
}
