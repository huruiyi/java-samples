package com.roy.language;

import java.util.Arrays;
import org.junit.Test;

public class InstanceofDemo {

  @Test
  public void test1() {
    Object o = "test";
    if (o instanceof Integer i && i > 0) {
      System.out.println(i.intValue());
    } else if (o instanceof String s && s.startsWith("t")) {
      System.out.println(s.charAt(0));
    }
  }

  @Test
  public void test2() {
    var nums = new int[]{1, 2, 3, 4, 5};
    var sum = Arrays.stream(nums).sum();
    System.out.println("数组之和为：" + sum);
  }
}
