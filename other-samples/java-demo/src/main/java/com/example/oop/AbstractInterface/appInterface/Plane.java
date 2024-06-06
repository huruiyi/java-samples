package com.example.oop.AbstractInterface.appInterface;

public class Plane implements IFlyable {

  public void fly() {
    System.out.println("Plane:I can fly");
  }

  public void refuel() {
    System.out.println("Plane:I need to refuel");
  }
}
