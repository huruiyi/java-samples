package com.example.thread;

abstract class Panret {

  public abstract void add();
}

class CreateThreadDemo03 implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 30; i++) {
      System.out.println("子线程 run,i:" + i);
    }
  }

}


class ThreadDemo3 {

  /**
   * 什么是进程， 进程就是正在运行的应用程序，进程是线程的集合。 什么是线程，线程就是一条执行路径，一个独立的执行单元。 什么是多线程 为了提高程序效率 创建有哪些方式？ 1、使用继承Therad类方式 继承Thread类重写run方法 2、使用实现runlabe接口方式
   * 3、使用匿名内部类方式 4、callable 5、使用线程池创建线程。
   */
  public static void main(String[] args) {
    // 什麼是匿名 內部类
    Panret panret = new Panret() {
      @Override
      public void add() {
        System.out.println("使用自定义内部类");
      }
    };
    panret.add();

    Thread t1 = new Thread(new Runnable() {

      @Override
      public void run() {
        // 需要线程执行的代码
        for (int i = 0; i < 30; i++) {
          System.out.println("子线程，i:" + i);
        }
      }
    });
    t1.start();
    for (int i = 0; i < 30; i++) {
      System.out.println("主线程，i:" + i);
    }
  }

}
