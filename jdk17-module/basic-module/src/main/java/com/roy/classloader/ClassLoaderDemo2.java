package com.roy.classloader;


public class ClassLoaderDemo2 {

  public static void main(String[] args) throws Exception {
    DemoModuleClassLoader demoModuleClassLoader = new DemoModuleClassLoader("/Users/roykingw/DevCode/JDK17Demo/out/artifacts/basicModule_jar");
    Class<?> tClazz = demoModuleClassLoader.loadClass("com.roy.language.TextDemo");
    Object textDemo = tClazz.newInstance();
    tClazz.getMethod("test1").invoke(textDemo, null);
  }
}
