package vip.fairy.security;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;

public class RSAEncrypt {

  private static final Map<Integer, String> keyMap = new HashMap<>();  //用于封装随机产生的公钥与私钥

  static RSAPrivateKey privateKey;
  static RSAPublicKey publicKey;

  public static void main(String[] args) throws Exception {
    //生成公钥和私钥
    genKeyPair();
    //加密字符串
    String message = "Hello World";
    System.out.println("随机生成的公钥为:" + keyMap.get(0));
    System.out.println("随机生成的私钥为:" + keyMap.get(1));

    String messageEn = encrypt(message, keyMap.get(0));
    System.out.println(message + "\t加密后的字符串为:" + messageEn);

    String messageDe = decrypt(messageEn, keyMap.get(1));
    System.out.println("还原后的字符串为:" + messageDe);

    String originalData = "hello world,世界你好！！";
    String signDataStr = signData(originalData, privateKey);
    boolean data = verifySignature(originalData, signDataStr, publicKey);
    System.out.println(data);
  }


  public static String signData(String data, PrivateKey privateKey) throws SignatureException {
    try {
      Signature signature = Signature.getInstance("SHA256withRSA");
      signature.initSign(privateKey);
      signature.update(data.getBytes(StandardCharsets.UTF_8));
      byte[] signedData = signature.sign();
      return java.util.Base64.getEncoder().encodeToString(signedData);
    } catch (Exception e) {
      throw new SignatureException("Failed to sign data", e);
    }
  }

  public static boolean verifySignature(String originalData, String signature, PublicKey publicKey) throws SignatureException {
    try {
      Signature sig = Signature.getInstance("SHA256withRSA");
      sig.initVerify(publicKey);
      sig.update(originalData.getBytes(StandardCharsets.UTF_8));
      byte[] signatureBytes = java.util.Base64.getDecoder().decode(signature);
      return sig.verify(signatureBytes);
    } catch (Exception e) {
      throw new SignatureException("Failed to verify signature", e);
    }
  }

  /**
   * 随机生成密钥对
   *
   * @throws NoSuchAlgorithmException
   */
  public static void genKeyPair() throws NoSuchAlgorithmException {
    // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
    // 初始化密钥对生成器，密钥大小为96-1024位
    keyPairGen.initialize(1024, new SecureRandom());
    // 生成一个密钥对，保存在keyPair中
    KeyPair keyPair = keyPairGen.generateKeyPair();
    privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
    publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
    String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
    // 得到私钥字符串
    String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
    // 将公钥和私钥保存到Map
    keyMap.put(0, publicKeyString);  //0表示公钥
    keyMap.put(1, privateKeyString);  //1表示私钥
  }

  /**
   * RSA公钥加密
   *
   * @param str       加密字符串
   * @param publicKey 公钥
   * @return 密文
   * @throws Exception 加密过程中的异常信息
   */
  public static String encrypt(String str, String publicKey) throws Exception {
    //base64编码的公钥
    byte[] decoded = Base64.decodeBase64(publicKey);
    RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
    //RSA加密
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, pubKey);
    return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
  }

  /**
   * RSA私钥解密
   *
   * @param str        加密字符串
   * @param privateKey 私钥
   * @return 铭文
   * @throws Exception 解密过程中的异常信息
   */
  public static String decrypt(String str, String privateKey) throws Exception {
    //64位解码加密后的字符串
    byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
    //base64编码的私钥
    byte[] decoded = Base64.decodeBase64(privateKey);
    RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
    //RSA解密
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, priKey);
    return new String(cipher.doFinal(inputByte));
  }

}

