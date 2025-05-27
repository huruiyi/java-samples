package vip.fairy.unfiled;

import java.util.HashMap;

public class MapDemo {

  public static void main(String[] args) {
    HashMap<Integer, String> maps = new HashMap<Integer, String>();
    maps.putIfAbsent(1, "aaa");
    maps.putIfAbsent(2, "bbb");
    maps.putIfAbsent(2, "bbb-bbb");
    System.out.println(maps);
  }


}
