package vip.fairy.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo2 {

  public static void main(String[] args) throws InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(6);

    for (int i = 1; i <= 6; i++) {

      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + "签到！");
        countDownLatch.countDown();
      }, "第" + i + "個人").start();


    }

    countDownLatch.await();

    System.out.println("老板宣布人夠了开始开会！");
  }
}
