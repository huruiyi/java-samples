package com.apress.prospring5.ch2.decoupled;

public interface MessageRenderer {

  void render();

  MessageProvider getMessageProvider();

  void setMessageProvider(MessageProvider provider);
}
