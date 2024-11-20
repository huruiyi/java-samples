package vip.fairy.oop.Decorator;

public class Benz implements Car {


  @Override
  public void Run() {
    System.out.println("Benz Run");
  }

  @Override
  public void Stop() {
    System.out.println("Benz Stop");
  }

}
