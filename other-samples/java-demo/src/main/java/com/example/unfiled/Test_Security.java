package com.example.unfiled;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;


public class Test_Security {

  @Test
  public void Test1() {
    try {
      String algorithm = "DES"; //定义加密算法,可用 DES,DESede,Blowfish
      String message = "Hello World. 这是待加密的信息";

      // 生成个DES密钥
      KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
      keyGenerator.init(56); //选择DES算法,密钥长度必须为56位
      Key key = keyGenerator.generateKey(); //生成密钥

      // 生成Cipher对象
      Cipher cipher = Cipher.getInstance("DES");

      //用密钥加密明文(message),生成密文(cipherText)
      cipher.init(Cipher.ENCRYPT_MODE, key);  //操作模式为加密(Cipher.ENCRYPT_MODE),key为密钥
      byte[] cipherText = cipher.doFinal(message.getBytes());  //得到加密后的字节数组
      System.out.println("加密后的信息: " + new String(cipherText));

      //用密钥加密明文(plainText),生成密文(cipherByte)
      cipher.init(Cipher.DECRYPT_MODE, key);  //操作模式为解密,key为密钥
      byte[] sourceText = cipher.doFinal(cipherText); //获得解密后字节数组
      System.out.println("解密后的信息: " + new String(sourceText));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Test
  public void Test2() throws Exception {
    String originalContent = "123456";
    Set<String> availableAlgorithms = Security.getAlgorithms("MessageDigest");
    for (String each : availableAlgorithms) {
      digest(each, originalContent.getBytes());
    }

    String fileMd5Info = getFileMd5Info("D:\\消息中间件(二期).xmind");
    System.out.println(fileMd5Info);
  }

  private void digest(String algorithm, byte[] content) throws Exception {
    MessageDigest instance = MessageDigest.getInstance(algorithm);
    instance.update(content);
    //当所有数据已被更新,调用digest()方法完成哈希计算,返回字节数组
    byte[] digest = instance.digest();
    System.out.println("算法=" + algorithm + ",摘要=" + DatatypeConverter.printHexBinary(digest));
  }

  private String getFileMd5Info(String filePath) throws IOException, NoSuchAlgorithmException {
    FileInputStream inputStream = new FileInputStream(filePath);
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    //DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigest);
    String message = DatatypeConverter.printHexBinary(messageDigest.digest());
    inputStream.close();
    return message;
  }
  //https://blog.csdn.net/aitangyong/article/details/53858338

}
