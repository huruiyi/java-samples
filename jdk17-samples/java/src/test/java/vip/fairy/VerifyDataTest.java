package vip.fairy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class VerifyDataTest {

  public static void main(String[] args) {
    try {
      // 待签名的原始数据
      String data = "这是一段需要签名的数据";
      System.out.println("原始数据: " + data);

      // 从PEM文件加载私钥
      PrivateKey privateKey = loadPrivateKeyFromPemFile("private_key.pem");

      // 生成签名
      String signature = signData(data, privateKey);
      System.out.println("签名结果: " + signature);

      // 从PEM文件加载公钥
      PublicKey publicKey = loadPublicKeyFromPemFile("public_key.pem");

      // 验证签名
      boolean isValid = verifySignature(data, signature, publicKey);
      System.out.println("签名验证结果: " + isValid);

      // 尝试用篡改的数据验证签名
      boolean isTamperedValid = verifySignature(data + "篡改", signature, publicKey);
      System.out.println("篡改数据后的验证结果: " + isTamperedValid);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 生成数字签名
  public static String signData(String data, PrivateKey privateKey) throws Exception {
    // 创建Signature对象，指定签名算法
    Signature signature = Signature.getInstance("SHA256withRSA");
    signature.initSign(privateKey);

    // 更新要签名的数据
    signature.update(data.getBytes(StandardCharsets.UTF_8));

    // 生成签名
    byte[] signBytes = signature.sign();

    // 将签名转换为Base64编码字符串以便存储或传输
    return Base64.getEncoder().encodeToString(signBytes);
  }

  // 验证数字签名
  public static boolean verifySignature(String data, String signatureStr, PublicKey publicKey) throws Exception {
    // 将Base64编码的签名转换回字节数组
    byte[] signatureBytes = Base64.getDecoder().decode(signatureStr);

    // 创建Signature对象，指定签名算法
    Signature signature = Signature.getInstance("SHA256withRSA");
    signature.initVerify(publicKey);

    // 更新要验证的数据
    signature.update(data.getBytes(StandardCharsets.UTF_8));

    // 验证签名
    return signature.verify(signatureBytes);
  }

  // 从PEM文件加载公钥（与之前程序相同）
  private static PublicKey loadPublicKeyFromPemFile(String fileName)
      throws IOException, GeneralSecurityException {
    String keyContent = new String(Files.readAllBytes(Paths.get(fileName)),
        StandardCharsets.UTF_8);

    // 移除PEM头和尾
    String publicKeyPEM = keyContent
        .replace("-----BEGIN PUBLIC KEY-----", "")
        .replace("-----END PUBLIC KEY-----", "")
        .replaceAll("\\s", "");

    // 解码Base64
    byte[] encoded = Base64.getDecoder().decode(publicKeyPEM);

    // 生成公钥
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
    return keyFactory.generatePublic(keySpec);
  }

  // 从PEM文件加载私钥（与之前程序相同）
  private static PrivateKey loadPrivateKeyFromPemFile(String fileName)
      throws IOException, GeneralSecurityException {
    Path path = Paths.get(fileName);

    String keyContent = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

    // 移除PEM头和尾
    String privateKeyPEM = keyContent
        .replace("-----BEGIN PRIVATE KEY-----", "")
        .replace("-----END PRIVATE KEY-----", "")
        .replaceAll("\\s", "");

    // 解码Base64
    byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);

    // 生成私钥
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
    return keyFactory.generatePrivate(keySpec);
  }
}
