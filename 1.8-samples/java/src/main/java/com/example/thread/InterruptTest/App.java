package com.example.thread.InterruptTest;

public class App {


  public static void main(String[] args) throws InterruptedException {

    class UserThread extends Thread {

      @Override
      public void run() {
        String name = Thread.currentThread().getName();
        long id = Thread.currentThread().getId();
        while (!Thread.currentThread().isInterrupted()) {
          System.out.println(name + "[" + id + "]" + " Is Running" + " isInterrupted is:" + Thread.currentThread().isInterrupted());
        }
        System.out.println(Thread.currentThread().isInterrupted());
      }
    }

    UserThread thread = new UserThread();
    thread.start();
    thread.sleep(20);
    thread.interrupt();
  }
}
