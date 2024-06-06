package com.example.oop.Demo6;


public class Animal {

  private int weight;

  public Animal(int theWeight) {
    weight = theWeight;
    System.out.println("执行Animal的构造方法");
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int w) {
    weight = w;
  }
}
