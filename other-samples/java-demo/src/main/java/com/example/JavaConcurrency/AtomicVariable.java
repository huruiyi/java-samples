package com.example.JavaConcurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者：胡睿毅 文件名：AtomicVariable 日期：2019/5/26 14:54
 **/
class CounterAtomic {

  public static Integer integer = 0;
  public static AtomicInteger atomicInteger = new AtomicInteger(0);
}

public class AtomicVariable {

  public static void main(String[] args) throws InterruptedException {
    Thread[] incremeterThread = new Incrementer[1000];
    Thread[] decrementerThread = new Decrementer[1000];

    for (int i = 0; i < 1000; i++) {
      incremeterThread[i] = new Incrementer();
      decrementerThread[i] = new Decrementer();
      incremeterThread[i].start();
      decrementerThread[i].start();
    }
    for (int i = 0; i < 1000; i++) {
      incremeterThread[i].join();
      decrementerThread[i].join();
    }
    System.out.printf("Integer value = %d AtomicInteger value = %d ", CounterAtomic.integer, CounterAtomic.atomicInteger.get());
  }

  static class Incrementer extends Thread {

    public void run() {
      CounterAtomic.integer++;
      CounterAtomic.atomicInteger.incrementAndGet();
    }
  }

  static class Decrementer extends Thread {

    public void run() {
      CounterAtomic.integer--;
      CounterAtomic.atomicInteger.decrementAndGet();
    }
  }
}
