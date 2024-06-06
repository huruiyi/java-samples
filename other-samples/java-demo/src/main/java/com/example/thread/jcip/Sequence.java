package com.example.thread.jcip;

import com.example.thread.jcip.annotations.GuardedBy;
import com.example.thread.jcip.annotations.ThreadSafe;

/**
 * Sequence
 *
 * @author Brian Goetz and Tim Peierls
 */

@ThreadSafe
public class Sequence {

  @GuardedBy("this")
  private int nextValue;

  public synchronized int getNext() {
    return nextValue++;
  }
}
