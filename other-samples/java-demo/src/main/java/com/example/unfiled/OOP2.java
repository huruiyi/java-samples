package com.example.unfiled;

class Father {

  private int age;// 定义私有变量保存年龄
  private String name;// 定义私有变量name

  public void walk() { // 函数（可被重写）
    System.out.println("哎，年龄大了跑不动了，慢慢走吧！");
  }

  public int getAge() {// 成员方法，获得Father中的age值
    return age;
  }

  public void setAge(int age) { // 成员方法，用于对Father类中的age变量赋值
    this.age = age;
  }

  public String getName() {// 成员方法，获得变量name的值
    return name;
  }

  public void setName(String name) {// 成员方法，用于对Father类中的name变量赋值
    this.name = name;
  }

  public void Speak() {// 可被子类重写的函数
    System.out.println("Speaking slowly！");
  }
}

// 子类继承父类
class firstson extends Father {

  private String intrest;

  public String getIntrest() {
    return intrest;
  }

  public void setIntrest(String hobby) {
    intrest = hobby;
  }

  public void work() {// 改写父类的方法
    System.out.println("快速的走。");
  }

  public void Speak() {// 改写父类的方法
    System.out.println("Speaking quickly!");
  }
}

class secondSon extends Father {

  public void work() {
    System.out.println("学走路，进行时！勿扰");
  }

  public void Speak() {
    System.out.println("呀呀学语！");
  }
}

public class OOP2 {

  public static void main(String[] args) {
    Father s = new Father();
    s.walk();
    s.setAge(60);
    s.setName("Blue");
    System.out.println(s.getAge());
    System.out.println(s.getName());
    s.Speak();
    System.out.println("**********************");

    firstson s1 = new firstson();
    s1.work();
    s1.setIntrest("Musics");
    s1.setAge(20);
    s1.setName("Rose");
    System.out.println(s1.getAge());
    System.out.println(s1.getName());
    System.out.println(s1.getIntrest());
    s1.Speak();
    System.out.println("********************");

    secondSon s2 = new secondSon();
    s2.setAge(0);
    s2.setName("Baby");
    System.out.println(s2.getAge());
    System.out.println(s2.getName());
    s2.work();
    s2.Speak();
  }
}
