package com.example.JavaConcurrency;

import java.util.Arrays;
import java.util.Optional;

/**
 * 作者：胡睿毅 文件名：CorrectStringSplitAndConcatenate 日期：2019/5/26 15:00
 **/
class CorrectStringSplitAndConcatenate {

  public static void main(String[] args) {
    String words[] = "the quick brown fox jumps over the lazy dog".split(" ");
    Optional<String> originalString = (Arrays.stream(words).parallel().reduce((a, b) -> a + " " + b));
    System.out.println(originalString.get());
  }
}
