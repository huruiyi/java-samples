package com.example.thread.jcip;

import com.example.thread.jcip.annotations.ThreadSafe;

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
