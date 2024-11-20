package vip.fairy.unfiled;

interface ISpeakChinese {

  void SayHi();
}

class Person implements ISpeakChinese {

  static {
    System.out.println("初始化.........");
  }

  public String Name;
  public int Age;
  public double Height;

  public Person() {

  }

  public Person(String name, int age, float height) {
    this.Name = name;
    this.Age = age;
    this.Height = height;
  }

  @Override
  public void SayHi() {
    System.out.println("我会说中国话");
  }

  public void ShowInfo() {
    System.out.println("Name = " + this.Name + ", Age = " + this.Age + " ,Heigt = " + this.Height);
  }
}

class Employee extends Person {

  public float Salary;

  public Employee() {

  }

  public Employee(String name, int age, float height, float salary) {
    this.Name = name;
    this.Age = age;
    this.Height = height;
    this.Salary = salary;
  }

  public void ShowEmployeeInfo() {
    super.ShowInfo();
    System.out.println("Salary = " + this.Salary);
  }

}

public class OOP1 {

  public static void main(String[] args) {
    Person p1 = new Person();
    p1.Name = "小王";
    p1.Age = 20;
    p1.Height = 1.72f;
    p1.SayHi();
    p1.ShowInfo();
    System.out.println("******************************************************");
    Person p2 = new Person("小梦", 25, 1.65f);
    p2.SayHi();
    p2.ShowInfo();
    System.out.println("******************************************************");
    Employee e1 = new Employee();
    e1.Name = "小雪";
    e1.Age = 30;
    e1.Salary = 8000;
    e1.Height = 1.66f;
    e1.SayHi();
    e1.ShowEmployeeInfo();
    System.out.println("******************************************************");
    Employee e2 = new Employee("小明", 26, 1.75f, 10000);
    e2.SayHi();
    e2.ShowEmployeeInfo();
    System.out.println("******************************************************");
  }
}
