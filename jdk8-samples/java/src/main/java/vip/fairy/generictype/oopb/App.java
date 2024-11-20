package vip.fairy.generictype.oopb;

import java.util.Arrays;
import java.util.Collections;

public class App {

  public static void main(String[] args) {
    Programmer[] p = new Programmer[3];
    p[0] = new Programmer(10000);
    p[1] = new Programmer(2000);
    p[2] = new Programmer(5000);

    for (Programmer programmer : p) {
      System.out.println(programmer.getSalary());
    }

    Arrays.sort(p, Collections.reverseOrder());

    for (Programmer programmer : p) {
      System.out.println(programmer.getSalary());
    }

    int[] a = new int[3];
    a[0] = 5;
    a[1] = -55;
    a[2] = 15;

    Arrays.sort(a);

    System.out.println(p[0].compareTo(p[1]));

  }
}
