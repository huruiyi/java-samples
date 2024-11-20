package vip.fairy.oop.Decorator;

public class CarDemo {

  public static void main(String[] args) {
    Car bmw = new Bmw();
    Car benz = new Benz();

    CarWrap carWrapBmw = new CarWrap(bmw);
    carWrapBmw.Run();
    carWrapBmw.Stop();

    CarWrap carWrapBenz = new CarWrap(benz);
    carWrapBenz.Run();
    carWrapBenz.Stop();
  }
}
