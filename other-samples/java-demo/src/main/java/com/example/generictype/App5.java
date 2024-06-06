package com.example.generictype;

import org.junit.jupiter.api.Test;

public class App5 {

  @Test
  void Test() {
    Point p1 = new Point(10, 20);
    Point p2 = new Point(4, 25);

    System.out.println(p1.x);
    System.out.println(p1.y);

    System.out.println(p2.x);
    System.out.println(p2.y);
  }

  class Point {

    int x;
    int y;

    Point() {
      System.out.println("This is default constructor");
      x = 100;
      y = 100;
    }

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}
