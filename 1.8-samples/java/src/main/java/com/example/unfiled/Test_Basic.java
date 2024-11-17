package com.example.unfiled;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.junit.jupiter.api.Test;

// Eclipse 快捷键
// Ctrl + F11 			 	运行
// Ctrl + D  				删除一行
// Ctrl + /					注释或取消注释
// Ctrl + Alt + ↑或↓			复制当前行向上或向下
// Ctrl + Shift + L  		查看所有注释,查看引用,查看文件内容
// Ctrl + Shift + X  		选中单词 转换 大写
// Ctrl + Shift + R  		查看文件
// Ctrl + Shift + Y  		选中单词 转换 小写
// Ctrl + Shift + A  		块编辑
// Ctrl + Shift + F  		代码格式化
// Ctrl + Shift + O  		导入jar包
// Ctrl + Shift + Enter 	跳转到上一行
// Shift + Enter   			 跳转到下一行
// Alt + /					智能提示
// Alt + ↑或↓				向上或向下移动当前行
// Ctrl + 2 + L				选中方法,再按L 自动声明变量
// Ctrl + 1					导入包
// Ctrl + O					预览方法签名
// Ctrl + /					代码折叠或展开(小键盘上的 / )
// Alt  + Shift + J 		代码参数说明

class Test_Basic {

  private static Scanner input;

  @Test
  public void PerfectNumber() {
    int i, m = 0, s;
    for (m = 1; m < 10000; m++) {
      s = 0;
      for (i = 1; i < m; i++) {
        if (m % i == 0) {
          s += i;
        }
      }

      if (s == m) {
        System.out.print(m + "是完数，其因子是：");
        for (i = 1; i < m; i++) {
          if (m % i == 0) {
            System.out.print(i + ",");
          }
        }
        System.out.println();
      }
    }
  }

  public boolean IsPrimer(int n) {
    int m = (int) Math.sqrt(n);
    for (int i = 2; i <= m; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  // 普通年（不能被100整除的年份）能被4整除的为闰年。（如2004年就是闰年,1999年不是闰年）；
  // 世纪年（能被100整除的年份）能被400整除的是闰年。(如2000年是闰年，1900年不是闰年)；
  public void LeapYear() {
    input = new Scanner(System.in);
    System.out.print("请输入年份：");
    int year = input.nextInt();

    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
      System.out.print(year + "年是闰年。");
    } else {
      System.out.print(year + "年不是闰年。");
    }
  }

  //region Switch
  @Test
  public void SwitchDemo1() {
    int read = 1;

    switch (read) {
      case 1:
        System.out.println("星期一");
        break;
      case 2:
        System.out.println("星期二");
        break;
      case 3:
        System.out.println("星期三");
        break;
      default:
        break;
    }
  }

  @Test
  public void SwitchDemo2() {
    int i;
    for (i = 0; i <= 5; i++) {
      switch (i) {
        case 1:
        case 2:
        case 3:
          System.out.println("i is 1, 2 or 3");
          break;
        case 4:
          System.out.println("i is 4");
          break;
      }
      System.out.println();
    }
  }

  @Test
  public void CalcDemo() {
    String flag = JOptionPane.showInputDialog("input the flag:");
    String numX = JOptionPane.showInputDialog("input the numX:");
    String numY = JOptionPane.showInputDialog("input the numY:");
    int x1 = Integer.parseInt(numX), y1 = Integer.parseInt(numY), numZ = 0;
    System.out.println(flag.getClass().toString());

    switch (flag) {
      case "+":
        numZ = x1 + y1;
        break;
      case "-":
        numZ = x1 - y1;
        break;
      case "*":
        numZ = x1 * y1;
        break;
      case "/":
        numZ = x1 / y1;
        break;
      default:
        System.out.println("输入的操作符号错误！");
    }

    System.out.printf("%d %s %d = %d\n", x1, flag, y1, numZ);
  }
  //endregion

  //region SystemInfo
  @Test
  public void exec01() {
    Runtime r = Runtime.getRuntime();
    Process p = null;
    try {
      String s = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe D:\\Oauth.txt -T png -o resultxxxxxxxxxxxx.png";
      p = r.exec(s);
    } catch (Exception e) {
      System.out.println("错误:" + e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  public void exec02() {
    Properties properties = System.getProperties();
    System.out.println(properties.size());
    Enumeration<Object> keys = properties.keys();
    int i = 0;
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = properties.get(key);
      System.out.println(++i + ":" + key + ":" + value);
    }
  }
  //endregion

  //region BigDecimal.compareTo

  @Test
  public void bCompare() {
    BigDecimal d1 = BigDecimal.valueOf(1);
    BigDecimal d2 = BigDecimal.valueOf(3);
    assertTrue(d1.compareTo(d2) < 0);
    assertTrue(d1.equals(BigDecimal.ONE));
  }
  //endregion
}
