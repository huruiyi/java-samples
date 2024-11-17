package com.roy.language;

import org.junit.Test;

public class SwitchDemo {

  @Test
  public void demo1() {
    String name = "李白";
    switch (name) {
      case "李白", "杜甫", "白居易" -> System.out.println("唐代诗人");
      case "苏轼", "辛弃疾" -> System.out.println("宋代诗人");
      default -> System.out.println("其他朝代诗人");
    }
  }

  @Test
  public void demo2() {
    String name = "楼兰";
    int tmp = switch (name) {
      case "李白", "杜甫", "白居易" -> 1;
      case "苏轼", "辛弃疾" -> 2;
      default -> {
        System.out.println("其他朝代诗人");
        yield 3;
      }
    };
    System.out.println(tmp);
  }
}
