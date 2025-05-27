package vip.fairy.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;

public class FTPClient {

  private Socket socket = null;
  private String ip = "localhost";
  private int port = 8082;

  public FTPClient() throws Exception {
    creationConnection();
  }

  public static void main(String[] args) throws Exception {
    new FTPClient().getFile();
  }

  private void creationConnection() throws Exception {
    socket = new Socket(ip, port);
  }

  private void getFile() throws Exception {
    String savePath = "I:\\";
    int bufferSize = 8193;
    byte[] buf = new byte[bufferSize];
    DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    savePath += dis.readUTF();
    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(savePath)));
    long length = dis.readLong();
    System.out.println("文件长度：" + length + "\n");
    while (true) {
      int read = 0;
      if (dis != null) {
        read = dis.read(buf);
      }
      if (read == -1) {
        break;
      }
      dos.write(buf, 0, read);
    }
    System.out.println("接收文件另存为：" + savePath + "\n");
    dos.close();
  }

}
