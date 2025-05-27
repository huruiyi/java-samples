package vip.fairy.fun;

import java.util.concurrent.TimeUnit;

public class Func {

  public int add(int a, int b) {
    return a + b;
  }

  public int sub(int a, int b) {
    return a - b;
  }

  public static String greeting() throws InterruptedException {
    // Thread.sleep(5000);
    TimeUnit.SECONDS.sleep(2);
    return "Hello, World!";
  }
}
