package vip.fairy.loader.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

  private String rootDir;

  public MyClassLoader(String rootDir) {
    this.rootDir = rootDir;
  }

  @Override
  public Class<?> findClass(String name) throws ClassNotFoundException {
    String extName = name.replace('.', '\\');
    String fileName = rootDir + "\\" + extName + ".class";
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      InputStream inputStream = new FileInputStream(fileName);
      int len = -1;
      byte[] b = new byte[1024];
      while ((len = inputStream.read(b)) != -1) {
        baos.write(b, 0, len);
      }
      baos.flush();
      baos.close();
      inputStream.close();
      byte[] byteArray = baos.toByteArray();
      return defineClass(name, byteArray, 0, byteArray.length);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
