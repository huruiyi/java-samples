package vip.fairy.thread.jcip;

import vip.fairy.thread.jcip.annotations.ThreadSafe;

/**
 * EagerInitialization
 * <p/>
 * Eager initialization
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class EagerInitialization {

  private static Resource resource = new Resource();

  public static Resource getResource() {
    return resource;
  }

  static class Resource {

  }
}
