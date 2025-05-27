package vip.fairy.text;

import java.util.StringJoiner;

public class StringDemo {

  public static void main(String[] args) {
    StringJoiner msgJoiner = new StringJoiner("");
    msgJoiner.add("Hello").add("\t").add("World");

    System.out.println(msgJoiner.length());
    System.out.println(msgJoiner);
  }

}
