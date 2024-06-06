package com.example.thread.jcip;

import org.junit.jupiter.api.Test;

/**
 * TestBoundedBuffer
 * <p/>
 * Basic unit tests for BoundedBuffer
 *
 * @author Brian Goetz and Tim Peierls
 */
public class TestBoundedBuffer {

  private static final long LOCKUP_DETECT_TIMEOUT = 1000;
  private static final int CAPACITY = 10000;
  private static final int THRESHOLD = 10000;

  @Test
  void testIsEmptyWhenConstructed() {
    SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<Integer>(10);
    System.out.println(bb.isEmpty());
    System.out.println(bb.isFull());
  }

  void testIsFullAfterPuts() throws InterruptedException {
    SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<Integer>(10);
    for (int i = 0; i < 10; i++) {
      bb.put(i);
    }
    System.out.println(bb.isFull());
    System.out.println(bb.isEmpty());
  }


  void testTakeBlocksWhenEmpty() {
    final SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<Integer>(10);
    Thread taker = new Thread() {
      public void run() {
        try {
          int unused = bb.take();
          System.err.println("error............");
        } catch (InterruptedException success) {
        }
      }
    };
    try {
      taker.start();
      Thread.sleep(LOCKUP_DETECT_TIMEOUT);
      taker.interrupt();
      taker.join(LOCKUP_DETECT_TIMEOUT);
      System.out.println(taker.isAlive());
    } catch (Exception unexpected) {
      System.err.println("error............");
    }
  }

  void testLeak() throws InterruptedException {
    SemaphoreBoundedBuffer<Big> bb = new SemaphoreBoundedBuffer<Big>(CAPACITY);
    int heapSize1 = snapshotHeap();
    for (int i = 0; i < CAPACITY; i++) {
      bb.put(new Big());
    }
    for (int i = 0; i < CAPACITY; i++) {
      bb.take();
    }
    int heapSize2 = snapshotHeap();
    System.out.println(Math.abs(heapSize1 - heapSize2) < THRESHOLD);
  }

  private int snapshotHeap() {
    /* Snapshot heap and return heap size */
    return 0;
  }

  class Big {

    double[] data = new double[100000];
  }

}
