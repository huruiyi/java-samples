package com.example.security;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class Jasypt {

  /**
   * Jasypt生成加密结果
   *
   * @param password 配置文件中设定的加密盐值
   * @param value    加密值
   * @return
   */
  public static String encyptPwd(String password, String value) {
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    encryptor.setConfig(cryptor(password));
    String result = encryptor.encrypt(value);
    return result;
  }

  /**
   * 解密
   *
   * @param password 配置文件中设定的加密盐值
   * @param value    解密密文
   * @return
   */
  public static String decyptPwd(String password, String value) {
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    encryptor.setConfig(cryptor(password));
    String result = encryptor.decrypt(value);
    return result;
  }

  public static SimpleStringPBEConfig cryptor(String password) {
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    config.setPassword(password);
    config.setAlgorithm("PBEWithMD5AndDES");
    config.setKeyObtentionIterations("1000");
    config.setPoolSize("1");
    config.setProviderName("SunJCE");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setStringOutputType("base64");
    return config;
  }


  public static void main(String[] args) {
    //<artifactId>jasypt-spring-boot-starter</artifactId>
    //<artifactId>jasypt-maven-plugin</artifactId>
    String encPwd = encyptPwd("jasypt", "123456");
    String decPwd = decyptPwd("jasypt", encPwd);
    System.out.println(encPwd);
    System.out.println(decPwd);
  }
}
