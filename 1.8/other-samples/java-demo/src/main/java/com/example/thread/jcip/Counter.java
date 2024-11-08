package com.example.thread.jcip;

import com.example.thread.jcip.annotations.GuardedBy;
import com.example.thread.jcip.annotations.ThreadSafe;

/**
 * Counter
 * <p/>
 * Simple thread-safe counter using the Java monitor pattern
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public final class Counter {

  @GuardedBy("this")
  private long value = 0;

  public synchronized long getValue() {
    return value;
  }

  public synchronized long increment() {
    if (value == Long.MAX_VALUE) {
      throw new IllegalStateException("counter overflow");
    }
    return ++value;
  }
}
