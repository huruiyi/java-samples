package com.example.bean_instantiation_mode;

public class Singleton {

  private static Singleton instance;

  static {
    instance = new Singleton();
  }

  private Singleton() {
    // needed so developers cannot instantiate this class directly
  }

  public static Singleton getInstance() {
    return instance;
  }
}
