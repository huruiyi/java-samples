package com.example.java_config_message_provider.model;

public class StandardOutMessageRenderer implements MessageRenderer {

  private MessageProvider messageProvider;

  public StandardOutMessageRenderer() {
    System.out.println(" --> StandardOutMessageRenderer: constructor called");
  }

  @Override
  public void render() {
    if (messageProvider == null) {
      throw new RuntimeException("You must set the property messageProvider of class:" + StandardOutMessageRenderer.class.getName());
    }
    System.out.println(messageProvider.getMessage());
  }

  @Override
  public MessageProvider getMessageProvider() {
    return this.messageProvider;
  }

  @Override
  public void setMessageProvider(MessageProvider provider) {
    System.out.println(" --> StandardOutMessageRenderer: setting the provider");
    this.messageProvider = provider;
  }
}
