package vip.fairy.unfiled;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class Randoms {

  @Test
  static void Test1() {
    System.out.println(Character.MAX_RADIX);
    System.out.println(36 * 36 * 36 * 36 * 36);

    Integer max36 = Integer.valueOf("ZZZZZ", 36);
    System.out.println(max36);    //36进制最大值
    String item = Integer.toUnsignedString(max36, 36);
    System.out.println(item);
  }

  @Test
  static void Test2() {
    for (int i = 0; i <= 60466175; i++) {
      String item = Integer.toUnsignedString(i, 36);
      System.out.println(item + "-" + Integer.valueOf(item, 36));
    }
  }

  @Test
  static void MathDemo() {
    int count = 0;
    for (int i = 1; i <= 10; i++) {
      int num1, num2, sum = 0;
      num1 = (int) (Math.random() * 10);
      num2 = (int) (Math.random() * 10);
      System.out.println(num1 + "+" + num2 + "=?");
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      try {
        sum = Integer.parseInt(in.readLine());
      } catch (Exception e) {
        e.printStackTrace();
      }

      if ((num1 + num2) == sum) {
        System.out.println("you are right! go on!");
        count++;
      } else {
        System.out.println("I'm sorry to tell you,you are wrong!");
      }
    }
    System.out.println("你做对了" + count + "个题目!");
  }

  @Test
  public void RandomDemo1() {
    Random num = new Random();
    for (int i = 0; i < 10; i++) {
      System.out.print(num.nextInt(100) + "\t");
    }
  }

  @Test
  public void RandomDemo2() {
    Random r1 = new Random(1234567890L);// 创建随机数对象
    Random r2 = new Random(1234567890L);
    boolean b1 = r1.nextBoolean();// 生成布尔类型的随机数
    boolean b2 = r1.nextBoolean();
    int i1 = r1.nextInt(100); // 生成整型随机数
    int i2 = r2.nextInt(100);
    double d1 = r1.nextDouble(); // 生成双精度随机数
    double d2 = r2.nextDouble();
    float f1 = r1.nextFloat(); // 生成单精度随机数
    float f2 = r1.nextFloat();
    System.out.print(b1 + " , " + b2); // 输出各个随机数
    System.out.println("  ,  " + i1 + ", " + i2);
    System.out.println(f1 + " , " + f2);
    System.out.print(d1 + " , " + d2);
  }

  @Test
  public void RandomDemo3() throws IOException {
    Random rd1 = new Random(); // 创建随机数类对象
    int num = 1 + rd1.nextInt(100); // 生成1~100范围的随机数
    System.out.println("猜测一个1~100范围的整数");
    System.out.print("请输入您猜的数：");
    long startTime = System.currentTimeMillis(), endTime;// 猜数开始时间和结束时间
    // 创建在命令行从输入键盘输入数据的字符缓冲流，目前先背下来
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();
    int guess = Integer.parseInt(s);// 转化为整数：第1次用户猜数
    int i = 1;// 猜数次数计数器
    while (guess != num) {// 没有猜中则继续循环
      System.out.println("第" + (i++) + "次猜数" + guess + (guess > num ? "大了!" : "小了!"));
      System.out.print("请输入您猜的数：");
      s = br.readLine();
      guess = Integer.parseInt(s);// //第i次用户猜数
    }
    System.out.println("第" + i + "次猜数" + guess + ",恭喜你猜中！");
    endTime = System.currentTimeMillis();
    System.out.println("猜中共用时：" + (endTime - startTime) / 1000.0 + "秒");
  }

  @Test
  public void CardShuffleDemo() {
    CardShuffle cardObj = new CardShuffle();
    cardObj.createCard();
    cardObj.display("洗牌前");
    cardObj.shuffle();
    cardObj.display("洗牌后");
  }

  static class CardShuffle {

    Random randomObj;
    int[] playCards;

    CardShuffle() {
      randomObj = new Random();
      playCards = new int[52];
    }

    void createCard() {
      for (int ctr = 0; ctr < 52; ctr++) {
        playCards[ctr] = ctr;
      }
    }

    void shuffle() {
      for (int ctr = 0; ctr < 52; ctr++) {
        int position = randomObj.nextInt(52);
        int temp = playCards[ctr];
        playCards[ctr] = playCards[position];
        playCards[position] = temp;
      }
    }

    void display(final String title) {
      System.out.println("\n*******************");
      System.out.println(title);
      System.out.println("*******************");
      for (int ctr = 0; ctr < 52; ctr++) {
        System.out.print("\t" + playCards[ctr]);
      }
    }
  }
}

