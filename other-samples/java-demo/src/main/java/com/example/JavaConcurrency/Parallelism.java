package com.example.JavaConcurrency;

import java.util.concurrent.ForkJoinPool;

/**
 * 作者：胡睿毅 文件名：Parallelism 日期：2019/5/26 15:00
 **/
public class Parallelism {

  public static void main(String[] args) {
    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
    System.out.println(ForkJoinPool.commonPool().getParallelism());
  }
}
