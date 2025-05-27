package vip.fairy.concurrent;

// This class exposes a publicly accessible counter
// to help demonstrate race condition problem
class CounterRunnable {

  public static long count = 0;
}

// This class implements Runnable interface
// Its run method increments the counter three times
class UseCounterRunnable implements Runnable {

  public void increment() {
    // increments the counter and prints the value
    // of the counter shared between threads
    CounterRunnable.count++;
    System.out.print(CounterRunnable.count + "  ");
  }

  public void run() {
    increment();
    increment();
    increment();
  }
}

public class RaceCondition {

  public static void main(String args[]) {
    UseCounterRunnable c = new UseCounterRunnable();
    Thread t1 = new Thread(c);
    Thread t2 = new Thread(c);
    Thread t3 = new Thread(c);
    t1.start();
    t2.start();
    t3.start();
  }

}
