package com.example.JavaConcurrency;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicIntegerTest {

  static AtomicInteger ai = new AtomicInteger(10);

  public static void check() {
    assert (ai.intValue() % 2) == 0;
  }

  public static void increment() {
    ai.incrementAndGet();
  }

  public static void decrement() {
    ai.getAndDecrement();
  }

  public static void compare() {
    ai.compareAndSet(10, 11);
  }

  public static void main(String[] args) {
    increment();
    decrement();
    compare();
    check();
    System.out.println(ai);
  }
}
