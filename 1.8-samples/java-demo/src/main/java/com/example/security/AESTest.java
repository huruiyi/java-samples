package com.example.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESTest {

  private static final Logger log = LoggerFactory.getLogger(AESTest.class);
  private String encodeResult;
  private String decodeResult;

  public static void main(String[] args) throws IOException {
    log.info("请输入待编码字符串(以回车键结束)："); //此处存在一个乱码问题，在文本文档中的编码是GBK而它的编码是UTF-8，cmd不识别！
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String source = br.readLine();

    AESTest e = new AESTest();
    e.setEncodeResult(source);
    log.info("编码后结果：{}", e.getEncodeResult());
    e.setDecodeResult(e.getEncodeResult());
    log.info("解码后结果：{}", e.getDecodeResult());
  }

  /**
   * AES加密为base 64 code
   *
   * @param content    待加密的内容
   * @param encryptKey 加密密钥
   * @return 加密后的base 64 code
   */
  public static String aesEncrypt(String content, String encryptKey) throws Exception {
    return base64Encode(aesEncryptToBytes(content, encryptKey));
  }

  /**
   * AES加密
   *
   * @param content    待加密的内容
   * @param encryptKey 加密密钥
   * @return 加密后的byte[]
   */

  public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
    KeyGenerator kgen = KeyGenerator.getInstance("AES");
    kgen.init(128, new SecureRandom(encryptKey.getBytes()));
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
    return cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * base 64 encode
   *
   * @param bytes 待编码的byte[]
   * @return 编码后的base 64 code
   */

  public static String base64Encode(byte[] bytes) {
    return new BASE64Encoder().encode(bytes);
  }

  /**
   * 将base 64 code AES解密
   *
   * @param encryptStr 待解密的base 64 code
   * @param decryptKey 解密密钥
   * @return 解密后的string
   */
  public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
    return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
  }

  /**
   * AES解密
   *
   * @param encryptBytes 待解密的byte[]
   * @param decryptKey   解密密钥
   * @return 解密后的String
   */
  public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
    KeyGenerator kgen = KeyGenerator.getInstance("AES");
    kgen.init(128, new SecureRandom(decryptKey.getBytes()));
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
    byte[] decryptBytes = cipher.doFinal(encryptBytes);
    return new String(decryptBytes);
  }

  /**
   * base 64 decode
   *
   * @param base64Code 待解码的base 64 code
   * @return 解码后的byte[]
   */
  public static byte[] base64Decode(String base64Code) throws Exception {
    return new BASE64Decoder().decodeBuffer(base64Code);
  }

  @Test
  void TestEncryptDecrypt() throws Exception {
    String content = "我爱你,祖国";
    log.info("加密前：{}", content);
    String key = "123456";
    log.info("加密密钥和解密密钥{}", key);
    String encrypt = aesEncrypt(content, key);
    log.info("加密后：{}", encrypt);
    String decrypt = aesDecrypt(encrypt, key);
    log.info("解密后：{}", decrypt);
  }

  public String getEncodeResult() {
    return encodeResult;
  }

  public void setEncodeResult(String encodeResult) {
    char[] src = encodeResult.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < src.length; i++) {
      if (Character.isDigit(src[i])) {
        if (i != src.length - 1) {
          char[] temp = new char[Character.getNumericValue(src[i]) + 1];
          Arrays.fill(temp, src[i + 1]);
          sb.append(temp);
          sb.append("_");
        } else {
          sb.append(src[i]);
        }
      } else if (src[i] == '_') {
        sb.append("\\UL");
        sb.append("_");
      } else if (i == src.length - 1) {
        sb.append(src[i]);
      } else {
        sb.append(src[i]);
        sb.append("_");
      }
    }
    this.encodeResult = new String(sb);//创建返回编码后字串
  }

  public String getDecodeResult() {
    return decodeResult;
  }

  public void setDecodeResult(String encodeResult) {
    String[] temp = encodeResult.split("_");
    StringBuilder sb = new StringBuilder();
    for (String s : temp) {
      if (s.equals("\\UL")) {
        sb.append("_");
      } else if (s.length() > 1) {
        sb.append(s.length() - 1);
      } else {
        sb.append(s);
      }
    }
    this.decodeResult = new String(sb);
  }

}
