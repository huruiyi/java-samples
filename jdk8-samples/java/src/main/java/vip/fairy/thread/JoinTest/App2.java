package vip.fairy.thread.JoinTest;

public class App2 {

  public static void main(String[] args) {
    test1();
  }

  private static void test0() {
    JoinThread t1 = new JoinThread();
    JoinThread t2 = new JoinThread();
    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    for (int i = 0; i < 100; i++) {
      System.out.println("main --" + i);
    }
  }

  private static void test1() {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 100; i++) {
          System.out.println(Thread.currentThread().getId() + "--" + i);
        }
      }
    };

    Thread t1 = new Thread(runnable, "t1");
    Thread t2 = new Thread(runnable, "t2");

    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    for (int i = 0; i < 100; i++) {
      System.out.println("main --" + i);
    }
  }
}
