package vip.fairy.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import org.junit.jupiter.api.Test;

public class SHATest {

  @Test
  void test1() throws NoSuchAlgorithmException {
    String password = "123456";
    String salt = "1abd1c";
    // 创建SHA-256摘要对象
    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
    messageDigest.update((password + salt).getBytes());
    // 计算哈希值
    byte[] result = messageDigest.digest();
    // 将哈希值转换为十六进制字符串
    String hexString = new HexBinaryAdapter().marshal(result);
    System.out.println("Original String: " + password);
    System.out.println("SHA-256 Hash: " + hexString.toLowerCase());
  }

  @Test
  void test2() throws NoSuchAlgorithmException {
    String password = "123456";
    String salt = "1abd1c";
    // 创建SHA-256摘要对象
    MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
    messageDigest.update((password + salt).getBytes());
    // 计算哈希值
    byte[] result = messageDigest.digest();
    // 将哈希值转换为十六进制字符串
    String hexString = new HexBinaryAdapter().marshal(result);
    System.out.println("Original String: " + password);
    System.out.println("SHA-256 Hash: " + hexString.toLowerCase());
  }

}
