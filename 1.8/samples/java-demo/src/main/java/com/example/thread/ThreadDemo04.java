package com.example.thread;


class CreateThread05 implements Runnable {

  @Override
  public void run() {

    for (int i = 0; i < 20; i++) {
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
      }

      System.out.println("线程id:" + Thread.currentThread().getId() + ":子线程 ,i:" + i + "name:" + Thread.currentThread().getName());
      if (i == 5) {
        Thread.currentThread().stop();// 不安全。不建议大家使用。
      }

    }

  }
}

public class ThreadDemo04 {

  public static void main(String[] args) {
    CreateThread05 t1 = new CreateThread05();
    Thread thread = new Thread(t1, "子线程");
    thread.start();
  }

}
