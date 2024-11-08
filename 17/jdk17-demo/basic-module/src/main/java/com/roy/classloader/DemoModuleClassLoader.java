package com.roy.classloader;

import java.security.SecureClassLoader;

public class DemoModuleClassLoader extends SecureClassLoader {

  private String modulePath;

  public DemoModuleClassLoader(String modulePath) {
    this.modulePath = modulePath;
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    return super.loadClass(name);
  }
}
