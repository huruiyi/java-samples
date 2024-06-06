package com.example.thread;

public class _03MainThread {

  public static void main(String[] args) throws Exception {
    Thread thread = new Thread() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          System.out.println("..............");
          e.printStackTrace();
        }
        System.out.println("A");
      }
    };
    thread.start();
    Thread.sleep(100);
    thread.stop();
    thread.interrupt();
    System.out.println("B");
  }
}
