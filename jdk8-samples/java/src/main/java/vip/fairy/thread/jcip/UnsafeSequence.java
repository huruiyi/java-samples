package vip.fairy.thread.jcip;

import vip.fairy.thread.jcip.annotations.NotThreadSafe;

/**
 * UnsafeSequence
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class UnsafeSequence {

  private int value;

  /**
   * Returns a unique value.
   */
  public int getNext() {
    return value++;
  }
}
