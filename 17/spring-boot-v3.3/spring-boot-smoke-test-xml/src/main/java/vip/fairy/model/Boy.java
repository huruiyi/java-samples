package vip.fairy.model;

import lombok.Data;

@Data
public class Boy {

  public String name;
  public int age;

  public Boy(String name, int age) {
    this.name = name;
    this.age = age;
  }

}
