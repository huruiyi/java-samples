package vip.fairy.unfiled;

import java.math.BigDecimal;

public class DataType {

  @SuppressWarnings("unused")
  private static void Demo1() {
    BigDecimal operand1 = new BigDecimal("1.0");
    BigDecimal operand2 = new BigDecimal("0.8");
    BigDecimal result = operand1.subtract(operand2);
    System.out.println(result);
  }

  @SuppressWarnings("unused")
  private static void Demo2() {
    int data1 = 10;
    int data2 = 20;

    Integer wrapper1 = new Integer(data1);
    Integer wrapper2 = new Integer(data2);

    System.out.println(data1 / 3);
    System.out.println(wrapper1.doubleValue() / 3);
    System.out.println(wrapper1.compareTo(wrapper2));
  }

  @SuppressWarnings("unused")
  private static void Demo3() {
    int x = 0;
    byte i = 0;
    short j = 0;
    long k = 0;
    float m = 0;
    double n = 0;
    boolean p = false;
    char q = 0;

    System.out.println("x=" + x);
    System.out.println("i=" + i);
    System.out.println("j=" + j);
    System.out.println("k=" + k);
    System.out.println("m=" + m);
    System.out.println("n=" + n);
    System.out.println("p=" + p);
    System.out.println("q=" + q);

  }

  public static void main(String[] args) {
    Demo3();
  }
}
