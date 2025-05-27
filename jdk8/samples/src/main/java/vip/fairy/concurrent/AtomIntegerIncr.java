package vip.fairy.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class AtomIntegerIncr implements Runnable {

  AtomicInteger atomicInteger = new AtomicInteger(0);
  CountDownLatch countDownLatch;

  public AtomIntegerIncr() {
  }

  public AtomIntegerIncr(CountDownLatch countDownLatch) {
    this.countDownLatch = countDownLatch;
  }

  @Override
  public void run() {
    try {
      int i = atomicInteger.get();
      countDownLatch.await();
      atomicInteger.set(i + 1);
      System.out.println(atomicInteger.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
