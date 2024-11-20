package vip.fairy.unfiled;

import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class Test_Final {

  static final int b = 123;
  static final int c;
  private static Scanner input;

  static {
    c = 123456;
  }

  @Test
  public void Demo0() {
    final int a = 2345;
    System.out.println(a);
//       a = 3456;
//       System.out.println(a);

    System.out.println(b);
//		b = 234;
//		System.out.println(b);
  }

  @Test
  public void Demo1() {
    String a = "hello2";

    final String b = "hello";
    //final修饰 ，相当于宏替换，编译后c的值为："hello2"
    String c = b + 2;
    System.out.println((a == c));

    String d = "hello";
    String e = d + 2;
    System.out.println((a == e));
  }


  @Test
  public void Demo2() {
    String a = "hello2";
    final String b = getHello();
    String c = b + 2;
    //这里要注意一点就是：不要以为某些数据是final就可以在编译期知道其值，通过变量b我们就知道了，在这里是使用getHello()方法对其进行初始化，他要在运行期才能知道其值。
    System.out.println((a == c));
  }

  public String getHello() {
    return "hello";
  }

  @Test
  public void Demo3() {
    final MyClass myClass = new MyClass();
    //这说明引用变量被final修饰之后，虽然不能再指向其他对象，但是它指向的对象的内容是可变的。
    System.out.println(++myClass.i);
    System.out.println(++myClass.i);
  }

  @Test
  public void Demo4() {
    int a = 123;
    System.out.println(a);
  }

  public void changeValue(final int i) {
    //final参数不可改变
    //i++;
    System.out.println(i);
  }

  @Test
  public void Demo5() {
    StringBuffer buffer = new StringBuffer("hello");
    changeValue(buffer);
    System.out.println(buffer);
  }

  public void changeValue(final StringBuffer buffer) {
    //final修饰引用类型的参数，不能再让其指向其他对象，但是对其所指向的内容是可以更改的。
    //buffer = new StringBuffer("hi");
    buffer.append(" ");
    buffer.append("world");
  }

  @Test
  public void Demo6() {
    //java采用的是值传递，对于引用变量，传递的是引用的值，也就是说让实参和形参同时指向了同一个对象，因此让形参重新指向另一个对象对实参并没有任何影响。
    StringBuffer buffer = new StringBuffer("hello");
    changeValue2(buffer);
    System.out.println(buffer);
  }

  public void changeValue2(StringBuffer buffer) {
    //buffer重新指向另一个对象
    buffer = new StringBuffer("hello");
    buffer.append(" ");
    buffer.append("world");
    System.out.println(buffer);
  }

  class MyClass {

    public int i = 0;
  }
}
