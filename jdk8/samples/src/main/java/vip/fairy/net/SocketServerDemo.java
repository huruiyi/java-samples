package vip.fairy.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo {

  int port = 2345;
  ServerSocket serverSocket;

  public SocketServerDemo() {
    try {
      serverSocket = new ServerSocket(port);
      System.out.println("start server at port " + port);

      while (true) {
        Socket client = serverSocket.accept();
        System.out.println("Connect: " + client.getInetAddress());
        DataInputStream in = new DataInputStream(client.getInputStream());
        DataOutputStream out = new DataOutputStream(client.getOutputStream());

        byte[] message = "Connect ok.This message is from server.".getBytes();
        out.write(message, 0, message.length);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new SocketServerDemo();
  }
}
