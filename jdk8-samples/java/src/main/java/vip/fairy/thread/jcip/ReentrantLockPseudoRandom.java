package vip.fairy.thread.jcip;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import vip.fairy.thread.jcip.annotations.ThreadSafe;

/**
 * ReentrantLockPseudoRandom
 * <p/>
 * Random number generator using ReentrantLock
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class ReentrantLockPseudoRandom extends PseudoRandom {

  private final Lock lock = new ReentrantLock(false);
  private int seed;

  ReentrantLockPseudoRandom(int seed) {
    this.seed = seed;
  }

  public int nextInt(int n) {
    lock.lock();
    try {
      int s = seed;
      seed = calculateNext(s);
      int remainder = s % n;
      return remainder > 0 ? remainder : remainder + n;
    } finally {
      lock.unlock();
    }
  }
}
