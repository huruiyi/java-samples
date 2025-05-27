package vip.fairy.oop.Decorator;

public class CarWrap implements Car {

  public Car car;

  public CarWrap(Car car) {
    this.car = car;
  }

  @Override
  public void Run() {
    System.out.println("Speed up");
    car.Run();
  }

  @Override
  public void Stop() {
    car.Stop();
  }

}
