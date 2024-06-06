package com.example.thread.jcip;

import com.example.thread.jcip.annotations.ThreadSafe;

/**
 * ResourceFactory
 * <p/>
 * Lazy initialization holder class idiom
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class ResourceFactory {

  public static Resource getResource() {
    return ResourceHolder.resource;
  }

  private static class ResourceHolder {

    public static Resource resource = new Resource();
  }

  static class Resource {

  }
}
