package vip.fairy.thread.jcip;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * DeadlockAvoidance
 * <p/>
 * Avoiding lock-ordering deadlock using tryLock
 *
 * @author Brian Goetz and Tim Peierls
 */
public class DeadlockAvoidance {

  private static final int DELAY_FIXED = 1;
  private static final int DELAY_RANDOM = 2;
  private static Random rnd = new Random();

  static long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
    return DELAY_FIXED;
  }

  static long getRandomDelayModulusNanos(long timeout, TimeUnit unit) {
    return DELAY_RANDOM;
  }

  public boolean transferMoney(Account fromAcct,
      Account toAcct,
      DollarAmount amount,
      long timeout,
      TimeUnit unit)
      throws InsufficientFundsException, InterruptedException {
    long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
    long randMod = getRandomDelayModulusNanos(timeout, unit);
    long stopTime = System.nanoTime() + unit.toNanos(timeout);

    while (true) {
      if (fromAcct.lock.tryLock()) {
        try {
          if (toAcct.lock.tryLock()) {
            try {
              if (fromAcct.getBalance().compareTo(amount) < 0) {
                throw new InsufficientFundsException();
              } else {
                fromAcct.debit(amount);
                toAcct.credit(amount);
                return true;
              }
            } finally {
              toAcct.lock.unlock();
            }
          }
        } finally {
          fromAcct.lock.unlock();
        }
      }
      if (System.nanoTime() < stopTime) {
        return false;
      }
      NANOSECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
    }
  }

  static class DollarAmount implements Comparable<DollarAmount> {

    DollarAmount(int dollars) {
    }

    public int compareTo(DollarAmount other) {
      return 0;
    }
  }

  class Account {

    public Lock lock;

    void debit(DollarAmount d) {
    }

    void credit(DollarAmount d) {
    }

    DollarAmount getBalance() {
      return null;
    }
  }

  class InsufficientFundsException extends Exception {

  }
}

