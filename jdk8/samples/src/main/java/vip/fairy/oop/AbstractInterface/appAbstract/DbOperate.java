package vip.fairy.oop.AbstractInterface.appAbstract;

/**
 * 1：包含一个或多个抽象方法，类本身也必须是抽象类 2: 抽象类可以包含非抽象方法 3: 抽象方法在子类中必须实现 4: 抽象类可定义字段 5: 抽象类不能直接实例化，可通过指向子类的变量
 */
public abstract class DbOperate {

  private String connectionString = "";

  public void openConnection() {
    System.out.println("openConnection....");
  }

  public abstract void delete();

  public abstract void update();

  public void closeConnection() {
    System.out.println("closeConnection....");
  }
}
