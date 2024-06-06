package com.example.thread;

import java.util.concurrent.CountDownLatch;

public class Counter {

  public static int count = 0;
  static CountDownLatch cdl = new CountDownLatch(1000);//这里的数字，开启几个线程就写几

  public synchronized static void inc() throws InterruptedException {//注意，如果不加上synchronized，由于并发写入，结果会小于1000
    Thread.sleep(1);
    count++;
    cdl.countDown();
  }

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 1000; i++) {
      new Thread(new Runnable() {
        public void run() {
          try {
            Counter.inc();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
      ).start();
    }
    cdl.await();//主线程等待子线程执行输出
    System.out.println(count);
  }
}
