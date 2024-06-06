package com.example.JavaConcurrency;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 作者：胡睿毅 文件名：COWList 日期：2019/5/26 14:57
 **/

public class COWList {

  public static void main(String[] args) {
    List<String> aList = new CopyOnWriteArrayList<>();
    aList.add("one");
    aList.add("two");
    aList.add("three");

    Iterator listIter = aList.iterator();
    while (listIter.hasNext()) {
      System.out.println(listIter.next());
      aList.add("four");
    }
  }
}
