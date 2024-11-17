package com.example.unfiled;

import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class Demo {

  @Test
  public void Demo00() {
    int score = 10;

    if (score >= 90) {
      System.out.println("A");
    }

    if (score >= 60 && score < 90) {
      System.out.println("B");
    }
    if (score < 60) {
      System.out.println("C");
    }
  }

  @Test
  public void Demo01() {
    int score = 25;
    String result = score >= 90 ? "A" : (score >= 60 ? "B" : (score >= 30 ? "C" : "D"));
    System.out.println(result);
  }

  @Test
  public void Demo02() {
    Scanner sc = new Scanner(System.in);

    while (true) {
      int score = sc.nextInt();

      if (score == -1) {
        break;
      }

      if (score > 100 || score < 0) {
        System.out.println("请输入1-100的正整数");
        continue;
      }

      String result = score >= 90 ? "A" : (score >= 60 ? "B" : (score >= 30 ? "C" : "D"));
      System.out.println(result);
    }
    System.out.println("程序退出!");
  }

  @Test
  public void Demo03() {
    int result = 0;
    for (int i = 1; i <= 10; i++) {
      result += fn(i);
    }
    System.out.println(result);
  }

  @Test
  public void Demo04() {
    int year = 2000;
    for (int i = year; i <= 2100; i++) {
      int days = 5;
      int week = (year - 1 + (year - 1) / 4 - (year - 1) / 100 + (year - 1) / 400 + days) % 7;
      System.out.println(week);
    }
  }

  @Test
  public void Demo05() {
    int days = getDays(2018, 10);
    System.out.println("这个年月的天数是：" + days);
  }

  @Test
  public void Demo06() {
    int year = 2016;
    int month = 3;
    int days = 24;

    //定义一个天数的和    31+29+24
    int totalDays = 0;
    //从1月循环到当前月-1, 把这每一个月的天数求和，加上当前的月的号
    for (int i = 1; i < month; i++) {
      //求每一个月的天数
      int mdays = getDays(year, i);
      totalDays = totalDays + mdays;
    }
    //加上当前月的号（24）
    totalDays += days;
    System.out.println(year + "-" + month + "-" + days + "是这一年的第" + totalDays + "天");
  }

  @Test
  public void Demo07() {
    int year = 2018;
    int month = 1;
    int days = 28;
    int week = getWeek(year, month, days);
    System.out.println(year + "-" + month + "-" + days + "是星期" + week);
  }

  /**
   * week = (year-1+(year-1)/4-(year-1)/100+(year-1)/400+days)%7
   */
  public int getWeek(int year, int month, int days) {
    //先计算指定年月日的是这一年的第多少天
    int totalDays = getTotalDays(year, month, days);

    int week = (year - 1 + (year - 1) / 4 - (year - 1) / 100 + (year - 1) / 400 + totalDays) % 7;
    return week;
  }

  /**
   * 给定年月日，求这个日子是这一年的第多少天
   */
  public int getTotalDays(int year, int month, int days) {
    //定义一个天数的和    31+29+24
    int totalDays = 0;
    //从1月循环到当前月-1, 把这每一个月的天数求和，加上当前的月的号
    for (int i = 1; i < month; i++) {
      //求每一个月的天数
      int mdays = getDays(year, i);
      totalDays += mdays;
    }
    //加上当前月的号（24）
    totalDays += days;
    return totalDays;
  }

  /**
   * 获取某年某月有多少天
   *
   * @param year
   * @param month
   * @return
   */
  public int getDays(int year, int month) {
        /*给定年和月(2030-4)，求出来这个年月的天数
            31天 ： 1 3 5 7 8 10 12
            30天：  4 6 9 11
            28天：  28（平年）
            29天：  29（闰年）//可以被400整除或者能被4整除但是不能被100整除
        */
    int mdays = 0;

    switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        mdays = 31;
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        mdays = 30;
        break;
      //可以被400整除或者能被4整除但是不能被100整除
      case 2:
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
          mdays = 29;
        } else {
          mdays = 28;
        }
        break;
    }

    return mdays;

  }

  /**
   * 递归方法
   *
   * @param a
   * @return
   */
  public int fn(int a) {
    int result = 1;
    if (a != 1) {
      result = a * fn(a - 1);
    }
    return result;
  }

  @Test
  public void CastDemo() {
    int a = (int) 5.4, b = 2;
    double c = 12, d = 15;

    int result = a / (int) d;
    System.out.println(result);

    double result2 = c / a;
    System.out.println(result2);

    System.out.println(1.0 / 5);
  }

  @Test
  public void DefaultValue() {

    //默认值:0
    int a[] = new int[2];

    //默认值:false
    boolean[] b = new boolean[2];

    //默认值:null
    String[] s = new String[2];

    for (int i = 0; i < 2; i++) {
      System.out.println(a[i]);
    }

    for (int i = 0; i < 2; i++) {
      System.out.println(b[i]);
    }

    for (int i = 0; i < 2; i++) {
      System.out.println(s[i]);
    }
  }
}
