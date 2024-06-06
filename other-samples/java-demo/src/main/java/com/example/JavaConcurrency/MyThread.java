package com.example.JavaConcurrency;

/**
 * 作者：胡睿毅 文件名：MyThread 日期：2019/5/26 14:45
 **/
class MyThread extends Thread {

  public static void main(String args[]) {
    Thread myThread = new MyThread();
    myThread.start();
    System.out.println("In main(); thread name: " + Thread.currentThread().getName());
  }

  public void run() {
    try {
      sleep(1000);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
      // ignore the InterruptedException - this is perhaps the one of the
      // very few of the exceptions in Java which is acceptable to ignore
    }
    System.out.println("In run(); thread name is: " + getName());
  }
}
