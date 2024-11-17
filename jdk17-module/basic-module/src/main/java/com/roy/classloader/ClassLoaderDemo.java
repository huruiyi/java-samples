package com.roy.classloader;

public class ClassLoaderDemo {

  public static void main(String[] args) {
    ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
    System.out.println(classLoader);
    System.out.println(classLoader.getParent());
    System.out.println(classLoader.getParent().getParent());
  }
}
