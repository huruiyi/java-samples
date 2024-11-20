package vip.fairy.oop.AbstractInterface.appInterface;

public interface IFlyable {

  // static修饰符定义静态方法
  static void staticMethod() {
    System.out.println("接口中的静态方法");
  }

  void fly();

  //1：接口的内部成员默认public:
  //2： default修饰了，就需要有实现。即default修饰的方法，子类不一定要实现
  //2: static修饰的方法，可直接通过:接口名.方法名()直接调用.子类不能实现
  //3: 没有经过default或static的，子类必须实现
  public default void refuel() {
    System.out.println("IFlyable:I need to refuel");
  }

  // default修饰符定义默认方法
  default void defaultMethod() {
    System.out.println("接口中的默认方法");
  }

}
