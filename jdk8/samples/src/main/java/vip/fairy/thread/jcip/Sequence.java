package vip.fairy.thread.jcip;

import vip.fairy.thread.jcip.annotations.GuardedBy;
import vip.fairy.thread.jcip.annotations.ThreadSafe;

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
