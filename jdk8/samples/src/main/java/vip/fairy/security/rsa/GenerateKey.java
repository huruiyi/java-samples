package vip.fairy.security.rsa;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

public class GenerateKey {

  private static final String BASE_PATH;

  static {
    if (SystemUtils.IS_OS_WINDOWS) {
      BASE_PATH = StringUtils.substring(Objects.requireNonNull(GenerateKey.class.getResource("")).getPath(), 1);
    } else {
      BASE_PATH = Objects.requireNonNull(GenerateKey.class.getResource("")).getPath();
    }
  }

  public static void main(String[] args) {
    try {
      // 生成RSA密钥对
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048); // 密钥长度
      KeyPair keyPair = keyPairGenerator.generateKeyPair();

      // 获取公钥和私钥
      PublicKey publicKey = keyPair.getPublic();
      PrivateKey privateKey = keyPair.getPrivate();

      // 保存公钥为PEM格式
      saveKeyToPemFile(publicKey, BASE_PATH + "public_key.pem", "PUBLIC KEY");
      System.out.println("公钥已保存到 public_key.pem");

      // 保存私钥为PEM格式
      saveKeyToPemFile(privateKey, BASE_PATH + "private_key.pem", "PRIVATE KEY");
      System.out.println("私钥已保存到 private_key.pem");

      // 可选：从文件加载公钥并验证
      PublicKey loadedPublicKey = loadPublicKeyFromPemFile("public_key.pem");
      System.out.println("从文件加载的公钥验证成功: " + publicKey.equals(loadedPublicKey));

    } catch (IOException | GeneralSecurityException e) {
      e.printStackTrace();
    }
  }

  // 将密钥保存为PEM格式文件
  private static void saveKeyToPemFile(Key key, String fileName, String keyType) throws IOException {
    byte[] keyBytes = key.getEncoded();
    String encodedKey = Base64.getEncoder().encodeToString(keyBytes);

    try (Writer writer = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8)) {
      writer.write("-----BEGIN " + keyType + "-----\n");
      writer.write(encodedKey);
      writer.write("\n-----END " + keyType + "-----\n");
    }
  }

  // 从PEM文件加载公钥
  private static PublicKey loadPublicKeyFromPemFile(String fileName) throws IOException, GeneralSecurityException {
    Path path = Paths.get(BASE_PATH + fileName);
    byte[] bytes = Files.readAllBytes(path);
    String keyContent = new String(bytes, StandardCharsets.UTF_8);

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

  // 从PEM文件加载私钥
  private static PrivateKey loadPrivateKeyFromPemFile(String fileName) throws IOException, GeneralSecurityException {
    String keyContent = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
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
