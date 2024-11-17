package com.example.java_config_message_provider.model;

public interface MessageRenderer {

  void render();

  MessageProvider getMessageProvider();

  void setMessageProvider(MessageProvider provider);
}
