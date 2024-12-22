package vip.fairy.loader;

import java.util.Objects;
import vip.fairy.loader.utils.MyClassLoader;

public class App {

  public static void main(String[] args) throws Exception {
    String rootDir = Objects.requireNonNull(ClassLoader.class.getResource("/")).getPath();
    MyClassLoader mcl = new MyClassLoader(rootDir);
    Class<?> clazz = mcl.findClass("javax.activation.MimeType");
    clazz.getDeclaredMethod("show").invoke(clazz.newInstance());
  }

}
