package vip.fairy.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESCryptography {

  /**
   * IV length: must be 8 bytes long
   */
  private static final String secretKey = "10293847";

  public static void main(String[] args) throws Exception {
    String content = "hello world!!世界你好！！";
    System.out.println("加密前 " + content);

    byte[] encrypted = DES_CBC_Encrypt(content.getBytes());
    System.out.println("加密后：" + Base64.getEncoder().encodeToString(encrypted));

    byte[] decrypted = DES_CBC_Decrypt(encrypted);
    System.out.println("解密后：" + new String(decrypted, StandardCharsets.UTF_8));
  }

  public static byte[] DES_CBC_Encrypt(byte[] content) throws Exception {
    DESKeySpec keySpec = new DESKeySpec(secretKey.getBytes());
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    IvParameterSpec ivParameterSpec = new IvParameterSpec(keySpec.getKey());
    cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generateSecret(keySpec), ivParameterSpec);

    return cipher.doFinal(content);
  }

  public static byte[] DES_CBC_Decrypt(byte[] content) throws Exception {
    DESKeySpec keySpec = new DESKeySpec(secretKey.getBytes());
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, keyFactory.generateSecret(keySpec), new IvParameterSpec(secretKey.getBytes()));
    return cipher.doFinal(content);
  }

}
