package vip.fairy.thread.JoinTest;

public class App5 {

  public static void main(String[] args) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + "执行完成");
      }
    };
    Thread t1 = new Thread(runnable, "t1");
    Thread t2 = new Thread(runnable, "t2");
    Thread t3 = new Thread(runnable, "t3");
    try {
      t1.start();
      t1.join();
      t2.start();
      t2.join();
      t3.start();
      t3.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
