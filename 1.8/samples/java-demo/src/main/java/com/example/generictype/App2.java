package com.example.generictype;


import org.junit.jupiter.api.Test;

public class App2 {

  @Test
  void Test() {
    Point p1 = new Point(10, 20);
    System.out.println(p1.x);
    System.out.println(p1.y);

    Point p2 = new Point(4, 25);
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

    Point(int first, int second) {
      x = first;
      y = second;
    }
  }
}
