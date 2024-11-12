package com.example.thread;

class StopDemoThread extends Thread {

  volatile boolean flag = true;

  @Override
  public synchronized void run() {
    while (flag) {
      try {
        this.sleep(1000);
        wait();
      } catch (Exception e) {
        stopThread();
      }
      System.out.println("run.....");
    }
  }

  public void stopThread() {
    flag = false;
    System.out.println("停止线程");
  }
}

public class ThreadDemo06_Stop {

  public static void main(String[] args) {
    System.out.println("main 创建成功...");
    StopDemoThread stop0 = new StopDemoThread();
    StopDemoThread stop1 = new StopDemoThread();
    stop0.start();
    stop1.start();
    System.out.println("主线程启动....");
    for (int i = 0; i < 35; i++) {
      try {
        Thread.sleep(300);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

      System.out.println("main i:" + i);
      if (i == 25) {
        // stop0.stopThread();
        // stop1.stopThread();
        stop0.interrupt();
        stop1.interrupt();
      }

    }
  }

}
