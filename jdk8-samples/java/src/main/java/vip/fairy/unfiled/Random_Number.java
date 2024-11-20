package vip.fairy.unfiled;

public class Random_Number {

  public static void main(String[] args) {
    System.out.println(Character.MAX_RADIX);
    Test1();
    System.out.println(36 * 36 * 36 * 36 * 36);
    Integer max36 = Integer.valueOf("ZZZZZZ", 36);
    System.out.println(max36);
  }

  static void Test1() {
    Integer max36 = Integer.valueOf("ZZZZZ", 36);
    System.out.println(max36);    //36进制最大值
    String item = Integer.toUnsignedString(max36, 36);
    System.out.println(item);
  }

  static void Test2() {
    for (int i = 0; i <= 60466175; i++) {
      String item = Integer.toUnsignedString(i, 36);
      System.out.println(item + "-" + Integer.valueOf(item, 36));
    }
  }
}
