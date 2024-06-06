package com.apress.prospring5.ch3.provider;

public class HelloWorldMessageProvider implements MessageProvider {

  public HelloWorldMessageProvider() {
    System.out.println(" --> HelloWorldMessageProvider: constructor caled");
  }

  @Override
  public String getMessage() {
    return "Hello World!";
  }
}
