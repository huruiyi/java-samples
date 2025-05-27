package vip.fairy.concurrent;

/**
 * 作者：胡睿毅 文件名：RunnableImpl 日期：2019/5/26 14:46
 **/

class RunnableImpl implements Runnable {

  public static void main(String args[]) throws Exception {
    Thread myThread = new Thread(new RunnableImpl());
    myThread.start();
    System.out.println("In main(); thread name is: " + Thread.currentThread().getName());
  }

  public void run() {
    System.out.println("In run(); thread name is: " + Thread.currentThread().getName());
  }
}
