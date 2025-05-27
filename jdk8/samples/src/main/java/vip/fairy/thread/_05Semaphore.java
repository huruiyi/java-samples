package vip.fairy.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class _05Semaphore {

  public static void main(String[] args) throws InterruptedException {
    Semaphore semaphore = new Semaphore(0);

    for (int i = 0; i < 5; i++) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep((new Random().nextInt(10) + 1) * 1000);
            System.out.println("currentThread:" + Thread.currentThread());
            semaphore.release();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }).start();
    }

    semaphore.acquire(1);
    System.out.println("ok");

  }
}
