package vip.fairy.oop.Decorator;

public class Bmw implements Car {

  @Override
  public void Run() {
    System.out.println("Bmw Run");
  }

  @Override
  public void Stop() {
    System.out.println("Bmw Stop");
  }

}
