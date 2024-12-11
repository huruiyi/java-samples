package vip.fairy.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;
import java.util.List;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

/**
 * &#064;Blog  https://blog.csdn.net/next_second
 * <p>
 * &#064;GitHub  https://github.com/YoungBear
 * <p>
 * 参考：https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html#Examples
 */
public class DgstUtils {

  /**
   * 常用摘要算法 算法名称是大小写不敏感的
   */
  private static final List<String> DGST_ALGORITHM = Arrays.asList(
      "SHA-256",
      "SHA-512",
      "SHA-512/256",
      "SHA3-256",
      "SHA3-512"

  );

  static {
    Security.addProvider(new BouncyCastleProvider());
  }

  /**
   * 计算数据的消息摘要
   *
   * @param data          数据
   * @param algorithmName 算法名称
   * @return 消息摘要值
   * @throws NoSuchAlgorithmException 异常
   * @throws NoSuchProviderException  异常
   */
  public static String digest(byte[] data, String algorithmName) throws NoSuchAlgorithmException, NoSuchProviderException {
    MessageDigest messageDigest = MessageDigest.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
    messageDigest.update(data);
    return Hex.toHexString(messageDigest.digest());
  }

  /**
   * 计算文件的摘要值
   *
   * @param file          文件
   * @param algorithmName 算法名
   * @return 摘要值
   * @throws IOException              异常
   * @throws NoSuchAlgorithmException 异常
   * @throws NoSuchProviderException  异常
   */
  public static String digest(File file, String algorithmName) throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
    if (file != null && file.exists() && file.canRead()) {
      byte[] buffer = new byte[1024];
      try (FileInputStream inputStream = new FileInputStream(file)) {
        int readSize = inputStream.read(buffer);
        MessageDigest messageDigest = MessageDigest.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        while (readSize != -1) {
          messageDigest.update(buffer, 0, readSize);
          readSize = inputStream.read(buffer);
        }
        return Hex.toHexString(messageDigest.digest());
      }
    }
    throw new RuntimeException("illegal file.");
  }
}
