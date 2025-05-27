package vip.fairy;

import com.google.common.io.Resources;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class PathUtils {

  String mainResourcePath = "\\src\\main\\resources\\";

  /**
   * <a href="https://blog.csdn.net/u011047968/article/details/107311462">...</a>
   * <a href="https://github.com/search?q=chapter-2-springmvc-quickstart&type=code">...</a>
   * 根据文件路径读取文件内容
   */
  public static void getFileContent(Object fileInPath) throws IOException {
    BufferedReader br = null;
    if (fileInPath == null) {
      return;
    }
    if (fileInPath instanceof String) {
      br = new BufferedReader(new FileReader((String) fileInPath));
    } else if (fileInPath instanceof InputStream) {
      br = new BufferedReader(new InputStreamReader((InputStream) fileInPath));
    }
    String line;
    if (br != null) {
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
      br.close();
    }
  }

  public void function1(String fileName) throws IOException {
    String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();
    System.out.println(path);
    String filePath = path + fileName;
    System.out.println(filePath);
    getFileContent(filePath);
  }

  /**
   * 直接通过文件名getPath来获取路径
   */
  public void function2(String fileName) throws IOException {
    String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)).getPath();
    System.out.println(path);
    String filePath = URLDecoder.decode(path, StandardCharsets.UTF_8);
    System.out.println(filePath);
    getFileContent(filePath);
  }

  /**
   * 直接通过文件名+getFile()来获取
   */
  public void function3(String fileName) throws IOException {
    String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)).getFile();
    System.out.println(path);
    String filePath = URLDecoder.decode(path, StandardCharsets.UTF_8);
    System.out.println(filePath);
    getFileContent(filePath);
  }

  /**
   * 直接使用getResourceAsStream方法获取流 springboot项目中需要使用此种方法，因为jar包中没有一个实际的路径存放文件
   */
  public void function4(String fileName) throws IOException {
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
    getFileContent(inputStream);

    if (inputStream != null) {
      String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      System.out.println(content);
    }
  }

  /**
   * 直接使用getResourceAsStream方法获取流 如果不使用getClassLoader，可以使用getResourceAsStream("/配置测试.txt")直接从resources根路径下获取
   */
  public void function5(String fileName) throws IOException {
    InputStream in = this.getClass().getResourceAsStream("/" + fileName);
    getFileContent(in);
  }


  /**
   * 通过绝对路径获取项目中文件的位置（不能用于服务器）
   */
  public void function7(String fileName) throws IOException {
    String rootPath = System.getProperty("user.dir");
    String filePath = rootPath + mainResourcePath + fileName;
    getFileContent(filePath);
  }

  public void function6(String fileName) throws IOException, URISyntaxException {
    Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI());
    byte[] fileContent = Files.readAllBytes(path);
    String content = new String(fileContent, StandardCharsets.UTF_8);
    System.out.println(content);
  }


  /**
   * 通过绝对路径获取项目中文件的位置（不能用于服务器）
   */
  public void function8(String fileName) throws IOException {
    //参数为空
    File directory = new File("");
    //规范路径：getCanonicalPath() 方法返回绝对路径，会把 ..\ 、.\ 这样的符号解析掉
    String rootCanonicalPath = directory.getCanonicalPath();
    //绝对路径：getAbsolutePath() 方法返回文件的绝对路径，如果构造的时候是全路径就直接返回全路径，如果构造时是相对路径，就返回当前目录的路径 + 构造 File 对象时的路径
    String rootAbsolutePath = directory.getAbsolutePath();
    System.out.println(rootCanonicalPath);
    System.out.println(rootAbsolutePath);
    String filePath = rootCanonicalPath + mainResourcePath + fileName;
    getFileContent(filePath);
  }

  /**
   * 通过绝对路径获取项目中文件的位置
   */
  public void function9(String fileName) throws IOException {
    System.setProperty("TEST_ROOT", "E:\\huruiyi\\java-daily-code\\spring\\spring-web");
    //参数为空
    String rootPath = System.getProperty("TEST_ROOT");
    System.out.println(rootPath);

    String testRoot = System.getenv("JAVA_HOME");
    System.out.println(testRoot);

    String filePath = rootPath + mainResourcePath + fileName;
    getFileContent(filePath);

  }

  public void function10(String fileName) throws IOException {
    URL resource = Resources.getResource(fileName);
    System.out.println(resource.getPath());
    System.out.println(resource.getFile());
    System.out.println(resource.getContent().toString());

    String fileContent = Resources.toString(resource, StandardCharsets.UTF_8);
    System.out.println(fileContent);
  }

  /**
   * 通过ClassPathResource类获取，建议SpringBoot中使用 springboot项目中需要使用此种方法，因为jar包中没有一个实际的路径存放文件
   */
  public void function11(String fileName) throws IOException {
    Resource classPathResource = new ClassPathResource(fileName);
    InputStream inputStream = classPathResource.getInputStream();
    String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    System.out.println(content);

    getFileContent(inputStream);
  }

  @Test
  void function12() throws IOException {
    File file = new File("src/main/resources/hello.txt");
    FileInputStream fis = new FileInputStream(file);
    byte[] fileContent = fis.readAllBytes();
    String content = new String(fileContent, StandardCharsets.UTF_8);
    System.out.println(content);
  }

  /**
   * 读取Properties文件
   */
  @Test
  void function13() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hello.properties");
    Properties properties = new Properties();
    properties.load(inputStream);

    String propertyValue = properties.getProperty("username");
    System.out.println(propertyValue);

    System.out.printf(System.getProperty("java.class.path"));
  }

  @Test
  void function14() throws IOException {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("hello.txt");
    if (inputStream != null) {
      String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      System.out.println(content);
    }
  }

  @Test
  void function15() {
    URL resource = ClassLoader.getSystemResource("hello.txt");
    String path = resource.getPath();
    System.out.println(path);

    String tPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("hello.txt")).getPath();
    System.out.printf(tPath);
  }

  @Test
  void function16() throws IOException {

    File f = new File(Objects.requireNonNull(this.getClass().getResource("/")).getPath());
    System.out.println("path1: " + f);

    File f2 = new File(Objects.requireNonNull(this.getClass().getResource("")).getPath());
    System.out.println("path1: " + f2);

    File directory = new File("");// 参数为空
    String courseFile = directory.getCanonicalPath();
    System.out.println("path2: " + courseFile);

    URL xmlPath = this.getClass().getClassLoader().getResource("");
    System.out.println("path3: " + xmlPath);

    System.out.println("path4:" + System.getProperty("user.dir"));

    System.out.println("path5: " + System.getProperty("java.class.path").split(";")[0]);

    System.out.println("path6: " + Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath());
  }

  @Test
  void test11() {
    Assertions.assertDoesNotThrow(() -> function11("hello.txt"));
  }

  @Test
  void test10() {
    Assertions.assertDoesNotThrow(() -> function10("hello.txt"));
  }

  @Test
  void test09() {
    Assertions.assertDoesNotThrow(() -> function9("hello.txt"));
  }

  @Test
  void test08() {
    Assertions.assertDoesNotThrow(() -> function8("hello.txt"));
  }

  @Test
  void test07() {
    Assertions.assertDoesNotThrow(() -> function7("hello.txt"));
  }

  @Test
  void test06() {
    Assertions.assertDoesNotThrow(() -> function6("hello.txt"));
  }

  @Test
  void test05() {
    Assertions.assertDoesNotThrow(() -> function5("hello.txt"));
  }

  @Test
  void test04() {
    Assertions.assertDoesNotThrow(() -> function4("hello.txt"));
  }

  @Test
  void test03() {
    Assertions.assertDoesNotThrow(() -> function3("hello.txt"));

  }

  @Test
  void test02() {
    Assertions.assertDoesNotThrow(() -> function2("hello.txt"));
  }

  @Test
  void test01() {
    Assertions.assertDoesNotThrow(() -> function1("test-hello.txt"));

  }
}
