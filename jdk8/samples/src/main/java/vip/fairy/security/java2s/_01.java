package vip.fairy.security.java2s;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Enumeration;

public class _01 {

  //1.	Exporting a Certificate to a File
  public static void main(String[] argv) throws Exception {
    FileInputStream is = new FileInputStream("yourfile" + ".keystore");
    KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
    String password = "my-keystore-password";
    keystore.load(is, password.toCharArray());

    Enumeration<String> e = keystore.aliases();
    while (e.hasMoreElements()) {
      String alias = (String) e.nextElement();
      boolean b = keystore.isKeyEntry(alias);
      b = keystore.isCertificateEntry(alias);
    }
    is.close();
  }
}
