package com.example.oop.AbstractInterface.appInterface;

public class App {

  public static void main(String[] args) {
    IFlyable flyable1 = new Bird();
    flyable1.fly();
    flyable1.refuel();

    IFlyable flyable2 = new Plane();
    flyable2.fly();
    flyable2.refuel();

    IFlyable.staticMethod();
  }
}
