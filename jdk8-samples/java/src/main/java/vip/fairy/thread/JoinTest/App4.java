package vip.fairy.thread.JoinTest;

public class App4 {

  public static void main(String[] args) {
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("t1 is finished");
      }
    });
    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          t1.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("t2 is finished");
      }
    });
    Thread t3 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          t2.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("t3 is finished");
      }
    });

    t3.start();
    t2.start();
    t1.start();

  }
}
