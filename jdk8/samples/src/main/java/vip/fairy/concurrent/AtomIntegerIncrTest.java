package vip.fairy.concurrent;

import java.util.concurrent.CountDownLatch;

public class AtomIntegerIncrTest {

  public static void main(String[] args) {
    CountDownLatch countDownLatch = new CountDownLatch(100);
    AtomIntegerIncr atomIntegerIncr = new AtomIntegerIncr(countDownLatch);

    for (int i = 0; i < 100; i++) {
      new Thread(atomIntegerIncr).start();
      countDownLatch.countDown();
    }
  }
}
