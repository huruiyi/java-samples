package vip.fairy.thread.jcip;

import vip.fairy.thread.jcip.annotations.ThreadSafe;

/**
 * SafeLazyInitialization
 * <p/>
 * Thread-safe lazy initialization
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SafeLazyInitialization {

  private static Resource resource;

  public synchronized static Resource getInstance() {
    if (resource == null) {
      resource = new Resource();
    }
    return resource;
  }

  static class Resource {

  }
}
