package vip.fairy.unfiled;

public class Operate {

  public static void main(String[] args) {

  }

  public static void Demo1() {
    int x = 6370;
    x = x / 1000 * 1000;
    System.out.println(x);
  }

  public static void Demo2() {
    int a = 3, b;
    b = (a++) + (++a) + (a++) + a;
    System.out.println("a=" + a + ",b=" + b);
  }

  public static void Demo3() {
    int i = 3;
    i = i++;
    System.out.println("i=" + i);
  }

  static void Demo4() {
    // 赋值运算符。= += -= *= /= %=
    int a, b, c;
    a = b = c = 4;
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
  }

  static void Demo5() {
    // 数据类型 变量名 = 初始化值;
    byte b = 3;
    short s = 4000;
    int x = 12;
    long l = 123l;
    float f = 2.3f;
    double d = 3.4;
    char ch = '1';
    boolean bl = true;
    bl = false;

    System.out.println(b);
    System.out.println(s);
    System.out.println(x);
    System.out.println(l);
    System.out.println(f);
    System.out.println(d);
    System.out.println(ch);
    System.out.println(bl);
    {
      int z = 9;
      System.out.println(z);
    }
  }

  static void Demo6() {
    System.out.println(Integer.MIN_VALUE);
    System.out.println(Integer.MAX_VALUE);

    System.out.println(Double.MIN_VALUE);
    System.out.println(Double.MAX_VALUE);
    System.out.println(Double.MIN_EXPONENT);
    System.out.println(Double.MAX_EXPONENT);
    System.out.println(Double.MIN_NORMAL);

    System.out.println(Float.MAX_VALUE);
    System.out.println(Float.MIN_VALUE);
    System.out.println(Float.MAX_EXPONENT);
    System.out.println(Float.MIN_EXPONENT);
    System.out.println(Float.MIN_NORMAL);

    System.out.println(Long.MAX_VALUE);
    System.out.println(Long.MIN_VALUE);
  }
}
