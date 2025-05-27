package vip.fairy.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class Array {

  private static Scanner scan;

  @Test
  static void Demo3() {
    int[][] cords = {{1, 2, 3}, {4, 5, 6}};
    System.out.println(cords.length);
  }

  public static void Transposition(int a[][]) {
    int i, j, tem = 0;
    for (i = 0; i < a.length; i++) {
      for (j = i + 1; j < a[i].length; j++) {
        tem = a[i][j];
        a[i][j] = a[j][i];
        a[j][i] = tem;
      }
    }
  }

  public static void PrintArray(int a[][]) {
    int i, j;
    for (i = 0; i < a.length; i++) {
      for (j = 0; j < a[i].length; j++) {
        System.out.printf("%3d", a[i][j]);
      }
      System.out.println();
    }
  }

  public static void InArray() {
    int[] ix = new int[10];
    int j;
    scan = new Scanner(System.in);
    for (j = 0; j < ix.length; j++) {
      System.out.printf("please input the num[%d] : ", j);
      ix[j] = scan.nextInt();
    }
    for (int m : ix) {
      System.out.printf("%3d,%s", m, Integer.toHexString(m));
      if (m % 3 == 0) {
        System.out.println();
      }
    }
  }

  public static void HashSetDemo() {
    HashSet<String> idlist = new HashSet<>();
    idlist.add("1");
    idlist.add("1");
    idlist.add("1");
    idlist.add("2");
    System.err.println(idlist.size());
  }

  @Test
  public void Demo1() {
    int[] scores = new int[10];
    for (int score : scores) {
      System.out.printf("%2d", score);
    }
    System.out.println();
    Arrays.fill(scores, 60);
    for (int score : scores) {
      System.out.printf("%3d", score);
    }
  }

  @Test
  public void Demo2() {
    int[] scores = {88, 81, 74, 68, 78, 76, 77, 85, 95, 93};
    for (int i = 0; i < scores.length; i++) {
      System.out.printf("分数：%d %n", scores[i]);
    }
  }

  @Test
  public void Demo4() {
    Integer[] scores = new Integer[3];
    for (Integer score : scores) {
      System.out.println(score);
    }
    scores[0] = new Integer(99);
    scores[1] = new Integer(87);
    scores[2] = new Integer(66);
    for (Integer score : scores) {
      System.out.println(score);
    }
  }

  @Test
  public void Demo5() {
    int[] scores1 = {88, 81, 74, 68, 78, 76, 77, 85, 95, 93};
    int[] scores2 = Arrays.copyOf(scores1, scores1.length);
    for (int score : scores2) {
      System.out.printf("%3d", score);
    }
    System.out.println();
    scores2[0] = 99;

    for (int score : scores1) {
      System.out.printf("%3d", score);
    }
  }

  @Test
  public void Demo9() {
    LinkedList<Integer> linkedList = new LinkedList<>();
    linkedList.add(3);
    linkedList.add(5);
    linkedList.add(2);

    linkedList.poll();
    System.out.println(linkedList.getFirst());

    System.out.println(linkedList.getLast());
  }

  @Test
  public void Demo6() {
    int i, j;
    int a[][] = new int[3][4];

    int b[][] = {{1, 5, 2, 8}, {5, 9, 10, -3}, {2, 7, -5, -18}};

    int c[][] = new int[3][4];
    for (i = 0; i < 3; i++) {
      for (j = 0; j < 4; j++) {
        a[i][j] = (i + 1) * (j + 2);
      }
    }
    for (i = 0; i < a.length; i++) {
      for (j = 0; j < a[i].length; j++) {
        c[i][j] = a[i][j] + b[i][j];
      }
    }

    System.out.println("*******Matrix C********");
    for (i = 0; i < c.length; i++) {
      for (j = 0; j < c[i].length; j++) {
        System.out.print(c[i][j] + " ");
      }
    }
  }

  @Test
  public void Demo7() {
    System.out.println("前100个素数是：");
    int n[] = new int[100];
    int p = 1, cn = 1;
    n[0] = 2;
    System.out.print("  " + p);
    for (int k = 1; k < 100; ) {
      p += 2;
      int j = 0;
      boolean flag = true;
      while (flag && n[j] * n[j] < p) {
        if (p % n[j] == 0) {
          flag = false;
        }
        j++;
      }
      if (flag == true) {
        System.out.print("  " + p); // p是素数时保存并输出
        n[k++] = p;
        cn++;
        if (cn == 10) {
          cn = 0;
          System.out.println();
        }
      }
    }
  }

  @Test
  public void Demo8() {
    int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    int i, j, tem = 0;
    for (i = 0; i < arr.length; i++) {
      for (j = i + 1; j < arr[i].length; j++) {
        tem = arr[i][j];
        arr[i][j] = arr[j][i];
        arr[j][i] = tem;
      }
    }
    for (i = 0; i < arr.length; i++) {
      for (j = 0; j < arr[i].length; j++) {
        System.out.printf("%3d", arr[i][j]);
      }
      System.out.println();
    }
  }

  @Test
  public void PrintArray() {
    String name = "Bill Gate";
    String[] list = {"C", "C++", "Java", "C#", "Python", "Go"};
    System.out.println(name.length());
    System.out.println(list.length);

    for (int i = 0; i < name.length(); i++) {
      System.out.print(name.charAt(i) + "  ");
    }
    System.out.println();
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + "  ");
    }
  }

  public void TranspositionDemo() {
    int[][] juzhen = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    System.out.println("原始矩阵是：");
    PrintArray(juzhen);
    System.out.println("实现转置后的矩阵是：");
    Transposition(juzhen);
    PrintArray(juzhen);
  }

}
