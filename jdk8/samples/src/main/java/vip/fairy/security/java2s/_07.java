package vip.fairy.security.java2s;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Collection;

public class _07 {

  //7.	This class imports a key and a certificate into a keystore
  private static InputStream fullStream(String fname) throws IOException {
    FileInputStream fis = new FileInputStream(fname);
    DataInputStream dis = new DataInputStream(fis);
    byte[] bytes = new byte[dis.available()];
    dis.readFully(bytes);
    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    return bais;
  }

  public static void main(String args[]) {

    // change this if you want another password by default
    String keypass = "password";

    // change this if you want another alias by default
    String defaultalias = "tomcat";

    // change this if you want another keystorefile by default
    String keystorename = null;

    // parsing command line input
    String keyfile = "";
    String certfile = "";
    if (args.length < 3 || args.length > 4) {
      System.out
          .println("Usage: java comu.ImportKey keystore keyfile certfile [alias]");
      System.exit(0);
    } else {
      keystorename = args[0];
      keyfile = args[1];
      certfile = args[2];
      if (args.length > 3) {
        defaultalias = args[3];
      }
    }

    try {
      // initializing and clearing keystore
      KeyStore ks = KeyStore.getInstance("JKS", "SUN");
      ks.load(null, keypass.toCharArray());
      System.out.println("Using keystore-file : " + keystorename);
      ks.store(new FileOutputStream(keystorename), keypass.toCharArray());
      ks.load(new FileInputStream(keystorename), keypass.toCharArray());

      // loading Key
      InputStream fl = fullStream(keyfile);
      byte[] key = new byte[fl.available()];
      KeyFactory kf = KeyFactory.getInstance("RSA");
      fl.read(key, 0, fl.available());
      fl.close();
      PKCS8EncodedKeySpec keysp = new PKCS8EncodedKeySpec(key);
      PrivateKey ff = kf.generatePrivate(keysp);

      // loading CertificateChain
      CertificateFactory cf = CertificateFactory.getInstance("X.509");
      InputStream certstream = fullStream(certfile);

      Collection c = cf.generateCertificates(certstream);
      Certificate[] certs = new Certificate[c.toArray().length];

      if (c.size() == 1) {
        certstream = fullStream(certfile);
        System.out.println("One certificate, no chain.");
        Certificate cert = cf.generateCertificate(certstream);
        certs[0] = cert;
      } else {
        System.out.println("Certificate chain length: " + c.size());
        certs = (Certificate[]) c.toArray(new Certificate[c.size()]);
      }

      // storing keystore
      ks.setKeyEntry(defaultalias, ff, keypass.toCharArray(), certs);
      System.out.println("Key and certificate stored.");
      System.out.println("Alias:" + defaultalias + "  Password:"
          + keypass);
      ks.store(new FileOutputStream(keystorename), keypass.toCharArray());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
