package com.example.jsr330;

public interface MessageRenderer {

  void render();

  void setMessageProvider(MessageProvider provider);

  MessageProvider getMessageProvider();

}
