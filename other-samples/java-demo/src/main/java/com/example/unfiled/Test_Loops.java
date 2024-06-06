package com.example.unfiled;

import javax.swing.JOptionPane;
import org.junit.jupiter.api.Test;

public class Test_Loops {


  @Test
  public void SqrtDemo() {
    int sqroot, rerror;
    for (int num = 1; num < 20; num++) {
      sqroot = (int) Math.sqrt(num);
      System.out.println("Square root of " + num + " is " + sqroot);
      rerror = num - (sqroot * sqroot);
      System.out.println("Rounding error is " + rerror);
      System.out.println();
    }
  }

  @Test
  public void DaffodilsDemo() {
    int i, j, k, n = 100, m = 1;
    while (n < 1000) {
      i = n / 100;
      j = (n - i * 100) / 10;
      k = n % 10;
      if ((i * i * i + j * j * j + k * k * k) == n) {
        System.out.println("找到第" + (m++) + "个水仙花数：" + n);
      }
      ++n;
    }

  }

  @Test
  public void WhileDemo1() {
    int k = 0;
    char char1 = 'a';
    while (char1 != 'q') {
      String inChar = JOptionPane.showInputDialog("Input the charctor : ");
      char1 = inChar.charAt(0);

      System.out.print(char1);
      k++;
    }
    System.out.println("共输入字符  " + k + "个");
  }

  @Test
  public void WhileDemo2() {
    int y = 1;
    while (y < 1) {
      System.out.println("y=" + y);
      y++;
    }
  }

  /* do while语句的特点：无论条件是否满足，循环体至少执行一次。 */
  @Test
  public void DoWhileDemo() {
    int x = 1;
    do {
      System.out.println("x=" + x);
      x++;
    } while (x < 1);
  }

  @Test
  public void Fordemo() {
    int x = 1;
    for (System.out.println("a"); x < 10; System.out.println("c")) {
      System.out.println("d");
      x++;
    }
  }
}
