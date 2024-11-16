package com.example.demo.sample_command_1;

public class App {

  public static void main(String[] args) {
    Receiver r = new Receiver();
    Command c = new ConcreteCommand(r);
    Invoker i = new Invoker();

    i.setCommand(c);
    i.executeCommand();
  }
}
