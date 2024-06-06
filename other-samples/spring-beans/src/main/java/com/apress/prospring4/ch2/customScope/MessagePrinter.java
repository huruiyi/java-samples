package com.apress.prospring4.ch2.customScope;

import com.apress.prospring4.ch2.customScope.service.MessageService;

public class MessagePrinter {

  final private MessageService service;

  public MessagePrinter(MessageService service) {
    this.service = service;
  }

  public void printMessage() {
    System.out.println(this.service.getMessage());
  }
}