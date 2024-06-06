package com.example.thread;

import com.sun.management.ThreadMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

public class _01StartThread {

  private static Runnable ThRuner = null;

  public static void Demo1() {

    int i = Runtime.getRuntime().availableProcessors();
    System.out.println("可用核心数：" + i);

    ThRuner = new Runnable() {
      @Override
      public void run() {
        for (int i = 1; i <= 50; i++) {
          System.out.println(i + "__" + Thread.currentThread() + "" + i + "\t");
        }
      }
    };
    Thread thread1 = new Thread(ThRuner);
    thread1.setName("1号");
    Thread thread2 = new Thread(ThRuner);
    thread2.setName("2号");
    Thread thread3 = new Thread(ThRuner);
    thread3.setName("3号");
    Thread thread4 = new Thread(ThRuner);
    thread4.setName("4号");
    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
  }

  public static void Demo2() {
    ThreadMXBean threadMXBean = (ThreadMXBean) ManagementFactory.getThreadMXBean();
    ThreadInfo[] dumpAllThreads = threadMXBean.dumpAllThreads(false, false);
    for (ThreadInfo info : dumpAllThreads) {
      System.out.println(info.getThreadId() + " " + info.getThreadName());
    }
  }

  public static void main(String[] args) {
    Demo1();
  }

  public class ThRuner implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 100; i++) {
        System.out.println(Thread.currentThread() + "" + i);
      }
    }
  }

}
