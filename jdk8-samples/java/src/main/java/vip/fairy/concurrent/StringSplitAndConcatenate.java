package vip.fairy.concurrent;

import java.util.Arrays;


class StringSplitAndConcatenate {

  public static void main(String[] args) {
    String words[] = "the quick brown fox jumps over the lazy dog".split(" ");
    Arrays.stream(words).forEach(StringConcatenated::concatStr);
    System.out.println(StringConcatenated.result);
  }

  static class StringConcatenated {

    public static String result = "";

    public static void concatStr(String str) {
      result = result + "-" + str;
    }
  }
}
