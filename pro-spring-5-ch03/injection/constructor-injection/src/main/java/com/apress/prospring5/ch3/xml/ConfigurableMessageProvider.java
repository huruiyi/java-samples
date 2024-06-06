package com.apress.prospring5.ch3.xml;


import com.apress.prospring5.ch3.MessageProvider;

public class ConfigurableMessageProvider implements MessageProvider {

  private String message = "Default message";

  public ConfigurableMessageProvider() {

  }

  public ConfigurableMessageProvider(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
