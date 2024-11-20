package vip.fairy.security;

import org.apache.commons.codec.binary.Base64;

public class _01Base64 {

  public static void main(String[] args) {
    String string = "abc123";
    //编码
    String encode = encode(string.getBytes());
    System.out.println(string + "\t编码后的字符串为：" + encode);
    //解码
    String decode = decode(encode.getBytes());
    System.out.println(encode + "\t字符串解码后为：" + decode);

  }


  public static String decode(byte[] bytes) {
    return new String(Base64.decodeBase64(bytes));
  }

  public static String encode(byte[] bytes) {
    return new String(Base64.encodeBase64(bytes));
  }
}
