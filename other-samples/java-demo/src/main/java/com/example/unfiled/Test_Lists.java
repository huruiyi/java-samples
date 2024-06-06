package com.example.unfiled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class Test_Lists {

  // 自定义方法：从1~36个数中随机选出k个数，模拟抽奖
  public static ArrayList<Integer> selectNums(int k) {
    Random rd = new Random();
    ArrayList<Integer> allNum = new ArrayList<Integer>(); // 创建数组列表对象，存放1~36个整数
    ArrayList<Integer> select = new ArrayList<Integer>(); // 创建数组列表对象，存放从allNum选出的7个整数
    for (int i = 0; i < 36; i++) {
      allNum.add(i + 1);
    }
    int x;
    for (int i = 0; i < k; i++) {
      x = rd.nextInt(36 - i);
      select.add(allNum.get(x));
      allNum.remove(x);
    }
    return select;
  }

  @Test
  public void Test01() {
    System.out.println("第1次从1~36中随机选出的7个数：");
    List<Integer> list1 = selectNums(7);
    System.out.println("\t" + list1.toString());
  }

  public void Test02() {
    System.out.println("第2次从1~36中随机选出的7个数：");
    List<Integer> list2 = selectNums(7);
    Iterator<Integer> it = list2.iterator();
    while (it.hasNext()) {
      System.out.print("\t" + it.next());
    }
  }

  @Test
  public void Test03() {
    System.out.println("\n第3次从1~36中随机选出的7个数：");
    List<Integer> list3 = selectNums(7);
    Object obj[] = list3.toArray();
    Arrays.sort(obj);
    for (int i = 0; i < obj.length; i++) {
      System.out.print("\t" + obj[i]);
    }
  }

  @Test
  public void Test04() {
    String[] arr1 = new String[]{
        "1", "2", "3", "4"
    };
    System.out.println(arr1);
    String[] arr2 = arr1.clone();
    System.out.println(arr2);
  }

  @Test
  public void Test05() {
    Clothes[] c1 = {new Clothes("red", 'L'), new Clothes("blue", 'M')};
    Clothes[] c2 = new Clothes[c1.length];
    for (int i = 0; i < c1.length; i++) {
      c2[i] = c1[i];
    }
    c1[0].color = "yellow";
    System.out.println(c2[0].color);
    System.out.println(c2[1].color);
  }

  @Test
  public void Test06() {
    int size = (int) Math.pow(10, 9);
    System.out.println(size);
    long begin = System.currentTimeMillis();

    byte[] bytes = new byte[size];
    for (int i = 0; i < size; i++) {
      bytes[i] = 1;
    }
    long end = System.currentTimeMillis();
    System.out.println(end - begin);
  }

  public class Clothes {

    String color;
    char size;

    Clothes(String color, char size) {
      this.color = color;
      this.size = size;
    }
  }
}
