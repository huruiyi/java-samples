package vip.fairy.thread.jcip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import vip.fairy.thread.jcip.annotations.NotThreadSafe;
import vip.fairy.thread.jcip.annotations.ThreadSafe;

/**
 * ListHelder
 * <p/>
 * Examples of thread-safe and non-thread-safe implementations of put-if-absent helper methods for List
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
class BadListHelper<E> {

  public List<E> list = Collections.synchronizedList(new ArrayList<E>());

  public synchronized boolean putIfAbsent(E x) {
    boolean absent = !list.contains(x);
    if (absent) {
      list.add(x);
    }
    return absent;
  }
}

@ThreadSafe
class GoodListHelper<E> {

  public List<E> list = Collections.synchronizedList(new ArrayList<E>());

  public boolean putIfAbsent(E x) {
    synchronized (list) {
      boolean absent = !list.contains(x);
      if (absent) {
        list.add(x);
      }
      return absent;
    }
  }
}
