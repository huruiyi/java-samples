package com.roy.hidden;

public class HiddenClass {

  static {
    System.out.println("HiddenClass");
  }

  public String sayHello(String name) {
    return "Hello, " + name;
  }

  public static void printHello(String name) {
    System.out.printf("""
        Hello, %s !
        Hello, HiddenClass !
        %n""", name);
  }
}
