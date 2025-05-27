package vip.fairy.thread.JoinTest;

public class App6 {

  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + "执行完成");
      }
    };
    Thread t1 = new Thread(runnable, "t1");
    Thread t2 = new Thread(runnable, "t2");
    Thread t3 = new Thread(runnable, "t3");
    t1.start();
    t1.wait();

    t2.start();
    t2.wait();

    t3.start();
    t3.wait();
  }
}
