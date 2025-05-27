package vip.fairy.security;

import com.mchange.lang.ByteUtils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

public class Md5Test {

  @Test
  void test0() {
    //apache-common-lang
    String md5En = DigestUtils.md5Hex("Hello World");
    System.out.println(md5En);
    //b10a8db164e0754105b7a99be72e3fe5

    String md5Ch = DigestUtils.md5Hex("你好，世界");
    System.out.println(md5Ch);
    //dbefd3ada018615b35588a01e216ae6e

    String md5ChEn = DigestUtils.md5Hex("Hello World,你好，世界！！");
    System.out.println(md5ChEn);
    //252afbeb396bf16b753be8f840e85b07
  }

  @Test
  void test1() throws NoSuchAlgorithmException {
    byte[] bytes = "Hello World,你好，世界！！".getBytes(StandardCharsets.US_ASCII);

    MessageDigest md5 = MessageDigest.getInstance("MD5");
    byte[] digest = md5.digest(bytes);
    System.out.println(ByteUtils.toHexAscii(digest));

    String s = org.springframework.util.DigestUtils.md5DigestAsHex(bytes);
    System.out.println(s);
  }

}
