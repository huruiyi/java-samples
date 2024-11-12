package com.example.unfiled;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import java.util.Stack;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;


public class Test_IO {

  static final float PI = 3.14f;
  private static Scanner scan;

  @Test
  public void GetByteArray() throws IOException {
    byte[] byteArray = IOUtils.toByteArray(new FileInputStream(new File("D:\\upload\\x.zip")));
  }

  @Test
  public void GetInputChar() {
    char read = '0';
    System.out.println("输入数据：");
    try {
      read = (char) System.in.read();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("输入数据：" + read);
  }

  @Test
  public void GetInputString() {
    System.out.print("输入");
    scan = new Scanner(System.in);
    String read = scan.nextLine();
    scan.nextFloat();
    scan.nextDouble();
    scan.nextByte();
    scan.nextInt();
    scan.nextLong();
    System.out.println("输入数据：" + read);
  }

  @Test
  public void GetInput() {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      System.out.print("请输入一个字符串：");
      String str = in.readLine();
      System.out.println("第一个：" + str);

      System.out.println("请输入第二个字符串：");
      String str2 = in.readLine();
      System.out.println("第二个：" + str2);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void GetOutput() {
    // 输出
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    try {
      for (int i = 0; i < 5; i++) {
        out.write("hello" + i);
        out.newLine();
      }
      out.close();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

  @Test
  public void GetCharCount() {
    try {
      System.out.println("请输入字符串：");
      byte[] b = new byte[5];
      int n = System.in.read(b);
      String s = new String(b, 0, n);
      System.out.println("输入的字符串为：" + s);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void GetLoopInput() {
    scan = new Scanner(System.in);
    System.out.println("请输入字符串：");
    while (true) {
      String line = scan.nextLine();
      if (line.equals("exit")) {
        break;
      }
      System.out.println(">>>" + line);
    }
  }

  @Test
  public void GetLoopInput2() {
    scan = new Scanner(System.in);
    Stack<String> s = new Stack<String>();
    while (scan.hasNext()) {// scan.hasNext()永远为真
      String str = scan.nextLine();
      if (!str.equals("-")) {
        s.push(str);
      } else if (!s.isEmpty()) {
        System.out.println(s.pop() + " ");
      }
    }
    // 这个语句永远无法执行
    System.out.println("(" + s.size() + "left on stack)");
  }

  @Test
  public void GetLoopInput3() {
    scan = new Scanner(System.in);
    Stack<String> s = new Stack<String>();
    while (!scan.hasNext("eof")) {
      // 通过判断scan.hasNext()的参数,当输入为字符串eof时，则停止控制台的输入。
      String str = scan.nextLine();
      if (!str.equals("-")) {
        s.push(str);
      } else if (!s.isEmpty()) {
        System.out.println(s.pop() + " ");
      }
    }
    System.out.println("(" + s.size() + "left on stack)");
  }

  @Test
  public void PutNextChar() {
    scan = new Scanner("123 asdf sd 45 789 sdf asdfl,sdf.sdfl,asdf    ......asdfkl    las");
    // s.useDelimiter(" |,|\\.");
    while (scan.hasNext()) {
      System.out.println(scan.next());
    }
  }

  @Test
  public void PutFileLine() throws FileNotFoundException {
    InputStream in = new FileInputStream(new File("C:\\AutoSubmit.java"));
    scan = new Scanner(in);
    while (scan.hasNextLine()) {
      System.out.println(scan.nextLine());
    }
  }

  @Test
  public void GetArea() {
    System.out.println("请输入半径:");
    scan = new Scanner(System.in);
    float fRadius;
    float fArea = 0;
    fRadius = scan.nextFloat();
    fArea = PI * fRadius * fRadius;
    System.out.println("半径为" + fRadius + "的圆面积为" + fArea);
  }

  @Test
  public void Test() {
    System.out.println("Hello");
    File file = new File("NUL:");
    System.out.println(file.getPath());

    FileChannel channel;
    try {
      System.out.println(new FileInputStream(file).getChannel());

      channel = new FileInputStream("NUL:").getChannel();
      System.out.println(channel);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
