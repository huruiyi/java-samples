package vip.fairy.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Des {
  private static final String ALGORITHM = "DES";

  public static String encrypt(String key, String data) throws Exception {
    SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, keySpec);
    byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
    return Base64.getEncoder().encodeToString(encrypted);
  }

  public static String decrypt(String key, String data) throws Exception {
    SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, keySpec);
    byte[] decoded = Base64.getDecoder().decode(data);
    byte[] decrypted = cipher.doFinal(decoded);
    return new String(decrypted, StandardCharsets.UTF_8);
  }

  public static void main(String[] args) throws Exception {
    String key = "mysecret";
    String data = "Hello, World!,世界你哈哦";
    System.out.println("原始: " + data);

    String encryptedData = encrypt(key, data);
    System.out.println("加密: " + encryptedData);

    String decryptedData = decrypt(key, encryptedData);
    System.out.println("解密: " + decryptedData);

  }
}
