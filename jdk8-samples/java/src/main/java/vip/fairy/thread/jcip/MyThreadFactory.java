package vip.fairy.thread.jcip;

import java.util.concurrent.ThreadFactory;

/**
 * MyThreadFactory
 * <p/>
 * Custom thread factory
 *
 * @author Brian Goetz and Tim Peierls
 */
public class MyThreadFactory implements ThreadFactory {

  private final String poolName;

  public MyThreadFactory(String poolName) {
    this.poolName = poolName;
  }

  public Thread newThread(Runnable runnable) {
    return new MyAppThread(runnable, poolName);
  }
}
