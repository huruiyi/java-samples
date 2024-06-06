package com.example.thread;


class CreateThreadDemo02 implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 30; i++) {
      System.out.println("----子线程 run,i:" + i);
    }
  }
}

class ThreadDemo2 {

  /**
   * 什么是进程， 进程就是正在运行的应用程序，进程是线程的集合。 什么是线程，线程就是一条执行路径，一个独立的执行单元。 什么是多线程 为了提高程序效率 创建有哪些方式？ 1、使用继承Therad类方式 继承Thread类重写run方法 2、使用实现runlabe接口方式
   * 3、使用匿名内部类方式 4、callable 5、使用线程池创建线程。
   */
  public static void main(String[] args) throws InterruptedException {
    CreateThreadDemo02 t1 = new CreateThreadDemo02();
    Thread thread = new Thread(t1);

    //线程必须要先start，才能join，只有启动了，才能对线程进行操作。

    thread.start();

    thread.join();

    for (int i = 0; i < 30; i++) {
      System.out.println("++++主线程 i:" + i);
    }

  }

}
