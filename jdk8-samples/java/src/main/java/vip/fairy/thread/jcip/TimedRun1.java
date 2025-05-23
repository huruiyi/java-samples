package vip.fairy.thread.jcip;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * InterruptBorrowedThread
 * <p/>
 * Scheduling an interrupt on a borrowed thread
 *
 * @author Brian Goetz and Tim Peierls
 */
public class TimedRun1 {

  private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);

  public static void timedRun(Runnable r,
      long timeout, TimeUnit unit) {
    final Thread taskThread = Thread.currentThread();
    cancelExec.schedule(new Runnable() {
      public void run() {
        taskThread.interrupt();
      }
    }, timeout, unit);
    r.run();
  }
}
