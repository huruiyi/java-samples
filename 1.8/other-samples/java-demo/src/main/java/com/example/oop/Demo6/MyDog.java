package com.example.oop.Demo6;

public class MyDog {

  public static Dog myDog;

  public static void main(String[] args) {
    myDog = new Dog();
    myDog.setWeight(50);
    System.out.println("My Dog's Weight is " + myDog.getWeight());
    myDog.Bark();
  }
}
