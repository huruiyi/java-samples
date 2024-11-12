package com.example.thread;

public class _02SimpkeOper {

  private long count = 0;

  public static void main(String[] args) throws InterruptedException {
    _02SimpkeOper simpkeOper = new _02SimpkeOper();

    Count count1 = new Count(simpkeOper);
    Count count2 = new Count(simpkeOper);
    count1.start();
    count2.start();
    Thread.sleep(50);

    System.out.println(simpkeOper.count);
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  // synchronized 保证数据同步
  public synchronized void incementCount() {
    count++;
  }

  private static class Count extends Thread {

    private _02SimpkeOper simpkeOper;

    public Count(_02SimpkeOper simpkeOper) {
      this.simpkeOper = simpkeOper;
    }

    @Override
    public void run() {
      for (int i = 0; i < 100000; i++) {
        simpkeOper.incementCount();
      }
    }
  }
}
