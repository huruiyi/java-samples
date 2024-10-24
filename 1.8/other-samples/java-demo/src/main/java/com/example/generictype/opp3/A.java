package com.example.generictype.opp3;

class A {

  private int tmp;

  A() {
    System.out.println("I'm from outer class A");
    B b = new B();
  }

  static class C {

    static double PI = 3.14;
  }

  class B {

    double sample;

    B() {
      System.out.println("I'm from inner class B");
      tmp = 5;
    }
  }
}
