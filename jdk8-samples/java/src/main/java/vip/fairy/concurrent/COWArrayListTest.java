package vip.fairy.concurrent;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * 作者：胡睿毅 文件名：COWArrayListTest 日期：2019/5/26 15:01
 **/
class COWArrayListTest {

  public static void main(String[] args) {
    List<Integer> aList = new CopyOnWriteArrayList<Integer>(); // LINE A
    aList.addAll(Arrays.asList(10, 20, 30, 40));
    System.out.println(aList);
  }
}
