package com.example.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 作者：胡睿毅 文件名：ModifyingList 日期：2019/5/26 14:56
 **/
public class ModifyingList {

  public static void main(String[] args) {
    List<String> aList = new ArrayList<>();
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
