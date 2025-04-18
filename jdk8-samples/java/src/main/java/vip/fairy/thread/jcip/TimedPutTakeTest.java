package vip.fairy.thread.jcip;

import java.util.concurrent.CyclicBarrier;

/**
 * TimedPutTakeTest
 * <p/>
 * Testing with a barrier-based timer
 *
 * @author Brian Goetz and Tim Peierls
 */
public class TimedPutTakeTest extends PutTakeTest {

  private BarrierTimer timer = new BarrierTimer();

  public TimedPutTakeTest(int cap, int pairs, int trials) {
    super(cap, pairs, trials);
    barrier = new CyclicBarrier(nPairs * 2 + 1, timer);
  }

  public static void main(String[] args) throws Exception {
    int tpt = 100000; // trials per thread
    for (int cap = 1; cap <= 1000; cap *= 10) {
      System.out.println("Capacity: " + cap);
      for (int pairs = 1; pairs <= 128; pairs *= 2) {
        TimedPutTakeTest t = new TimedPutTakeTest(cap, pairs, tpt);
        System.out.print("Pairs: " + pairs + "\t");
        t.test();
        System.out.print("\t");
        Thread.sleep(1000);
        t.test();
        System.out.println();
        Thread.sleep(1000);
      }
    }
    PutTakeTest.pool.shutdown();
  }

  public void test() {
    try {
      timer.clear();
      for (int i = 0; i < nPairs; i++) {
        pool.execute(new PutTakeTest.Producer());
        pool.execute(new PutTakeTest.Consumer());
      }
      barrier.await();
      barrier.await();
      long nsPerItem = timer.getTime() / (nPairs * (long) nTrials);
      System.out.print("Throughput: " + nsPerItem + " ns/item");
      System.out.println("***********************");
      System.out.println(putSum.get());
      System.out.println(takeSum.get());
      System.out.println("***********************");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
