package com.example.thread;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

class DeadThread extends Thread {

  static AtomicInteger at = new AtomicInteger();
  HashMap<Integer, Integer> map = new HashMap<>(2);

  public void run() {
    while (at.get() < 100000) {
      map.put(at.get(), at.get());
      at.incrementAndGet();
    }
  }
}

public class _04HashMapDeadLock {

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      DeadThread dt = new DeadThread();
      dt.start();
      System.out.println(dt.map.size());
    }
  }
}
