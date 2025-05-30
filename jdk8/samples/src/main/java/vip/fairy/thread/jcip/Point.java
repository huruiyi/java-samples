package vip.fairy.thread.jcip;

import vip.fairy.thread.jcip.annotations.Immutable;

/**
 * Point
 * <p/>
 * Immutable Point class used by DelegatingVehicleTracker
 *
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
public class Point {

  public final int x, y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
