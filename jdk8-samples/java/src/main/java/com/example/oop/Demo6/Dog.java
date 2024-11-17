package com.example.oop.Demo6;

public class Dog extends Animal {

  public Dog() {
    super(0);
    System.out.println("执行dog的构造方法");
  }

  public Dog(int weight) {
    super(weight);
  }

  public int getWeight() {
    return super.getWeight();
  }

  public void Bark() {
    System.out.println("Wang~~Wang~~~");
  }
}
