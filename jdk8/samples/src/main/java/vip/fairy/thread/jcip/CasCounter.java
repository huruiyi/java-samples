package vip.fairy.thread.jcip;

import vip.fairy.thread.jcip.annotations.ThreadSafe;

/**
 * CasCounter
 * <p/>
 * Nonblocking counter using CAS
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class CasCounter {

  private SimulatedCAS value;

  public int getValue() {
    return value.get();
  }

  public int increment() {
    int v;
    do {
      v = value.get();
    } while (v != value.compareAndSwap(v, v + 1));
    return v + 1;
  }
}
