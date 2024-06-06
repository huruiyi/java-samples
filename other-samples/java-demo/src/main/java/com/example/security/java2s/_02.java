package com.example.security.java2s;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Enumeration;

public class _02 {

  //2.	Listing the Aliases in a Key Store: A key store is a collection of keys and certificates.
  public static void main(String[] argv) throws Exception {
    FileInputStream is = new FileInputStream("yourfile" + ".keystore");
    KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
    String password = "my-keystore-password";
    keystore.load(is, password.toCharArray());

    Enumeration e = keystore.aliases();
    for (; e.hasMoreElements(); ) {
      String alias = (String) e.nextElement();
      boolean b = keystore.isKeyEntry(alias);
      b = keystore.isCertificateEntry(alias);
    }
    is.close();
  }
}
