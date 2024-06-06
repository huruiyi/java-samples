package com.apress.prospring5.ch3;

public interface NewsletterSender {

  String getSmtpServer();

  void setSmtpServer(String smtpServer);

  String getFromAddress();

  void setFromAddress(String fromAddress);

  void send();
}
