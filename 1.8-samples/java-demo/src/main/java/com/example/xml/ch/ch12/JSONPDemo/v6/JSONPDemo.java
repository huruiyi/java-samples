package com.example.xml.ch.ch12.JSONPDemo.v6;

import static java.lang.System.err;
import static java.lang.System.out;

import javax.json.Json;

public class JSONPDemo {

  public static void main(String[] args) {
    if (args.length != 1) {
      err.println("usage: java JSONPDemo pointer");
      return;
    }
    String encPointer = Json.encodePointer(args[0]);
    out.printf("Encoded pointer: %s%n\n", encPointer);
    out.printf("Decoded pointer: %s%n\n", Json.decodePointer(encPointer));
  }
}
