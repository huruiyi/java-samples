package com.example.oop.AbstractInterface.appInterface;

public class Bird implements IFlyable {

  public void fly() {
    System.out.println("Bird:I can fly");
  }

  public void refuel() {
    System.out.println("Bird:I need not to refuel");
  }


}
