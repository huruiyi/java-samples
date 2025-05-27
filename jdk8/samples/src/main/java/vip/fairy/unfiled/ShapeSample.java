package vip.fairy.unfiled;

import java.util.Scanner;

public class ShapeSample {

  public static void main(String[] args) {
    System.out.println("请选择你要打印的图形:1-金字塔;2-菱形;3-空心菱形;4-飞镖;");
    Scanner sc = new Scanner(System.in);
    //　接收打印的类型
    int type = sc.nextInt();
    System.out.println("请输入要打印的行数:");
    int num = sc.nextInt();
    switch (type) {
      case 1:
        drawUpTringle(num);
        break;
      case 2:
        drawRhombic(num);
        break;
      case 3:
        drawHollowRhombic(num);
        break;
      case 4:
        drawDart(num);
        break;
    }


  }

  /**
   * 打印正三角形 金字塔
   */
  public static void drawUpTringle(int num) {
    // 循环打印每行  i 是行号
    for (int i = 1; i <= num; i++) {
      // 循环打印空格 j是空格的顺序
      for (int j = 1; j <= num - i; j++) {
        System.out.print(" ");
      }
      // 循环打印星号 k是星号的顺序
      for (int k = 1; k <= i * 2 - 1; k++) {
        System.out.print("*");
      }
      System.out.println();
    }
  }

  /**
   * 打印倒三角形 金字塔
   */
  public static void drawDownTringle(int num) {
    // 循环打印每行  i 是行号
    for (int i = num; i >= 1; i--) {
      // 循环打印空格 j是空格的顺序
      for (int j = 1; j <= num - i; j++) {
        System.out.print(" ");
      }
      // 循环打印星号 k是星号的顺序
      for (int k = 1; k <= i * 2 - 1; k++) {
        System.out.print("*");
      }
      System.out.println();
    }
  }

  /**
   * 打印实心菱形
   */
  public static void drawRhombic(int num) {
    // 循环打印每行  i 是行号
    for (int i = 1; i <= num; i++) {
      // 循环打印空格 j是空格的顺序
      for (int j = 1; j <= num - i; j++) {
        System.out.print(" ");
      }
      // 循环打印星号 k是星号的顺序
      for (int k = 1; k <= i * 2 - 1; k++) {
        System.out.print("*");
      }
      System.out.println();
    }
    // 循环打印每行  i 是行号
    for (int i = num - 1; i >= 1; i--) {
      // 循环打印空格 j是空格的顺序
      for (int j = 1; j <= num - i; j++) {
        System.out.print(" ");
      }
      // 循环打印星号 k是星号的顺序
      for (int k = 1; k <= i * 2 - 1; k++) {
        System.out.print("*");
      }
      System.out.println();
    }
  }

  /**
   * 打印空心菱形
   */
  public static void drawHollowRhombic(int num) {
    // 循环打印每行  i 是行号
    for (int i = 1; i <= num; i++) {
      // 循环打印空格 j是空格的顺序
      for (int j = 1; j <= num - i; j++) {
        System.out.print(" ");
      }
      // 循环打印星号 k是星号的顺序
      for (int k = 1; k <= i * 2 - 1; k++) {
        if (k == 1 || k == i * 2 - 1) {
          System.out.print("*");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    // 循环打印每行  i 是行号
    for (int i = num - 1; i >= 1; i--) {
      // 循环打印空格 j是空格的顺序
      for (int j = 1; j <= num - i; j++) {
        System.out.print(" ");
      }
      // 循环打印星号 k是星号的顺序
      for (int k = 1; k <= i * 2 - 1; k++) {
        if (k == 1 || k == i * 2 - 1) {
          System.out.print("*");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }

  /**
   * 打印飞镖
   */
  public static void drawDart(int num) {
    // 循环打印每行  i 是行号
    for (int i = 1; i <= num; i++) {
      // 循环打印空格 j是空格的顺序
      for (int j = 1; j <= num - i; j++) {
        System.out.print(" ");
      }
      // 循环打印星号 k是星号的顺序
      for (int k = 1; k <= i * 2 - 1; k++) {
        if (k == 1 || k == i * 2 - 1 || k <= i) {
          System.out.print("*");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    // 循环打印每行  i 是行号
    for (int i = num - 1; i >= 1; i--) {
      // 循环打印空格 j是空格的顺序
      for (int j = 1; j <= num - i; j++) {
        System.out.print(" ");
      }
      // 循环打印星号 k是星号的顺序
      for (int k = 1; k <= i * 2 - 1; k++) {
        if (k == 1 || k == i * 2 - 1 || k >= i) {
          System.out.print("*");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }
}



