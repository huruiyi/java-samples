package com.mastering.spring.reactive;

import java.util.function.Consumer;

public class WelcomeConsumer implements Consumer<String> {
  @Override
  public void accept(String t) {
    System.out.println("Welcome " + t);
  }
}