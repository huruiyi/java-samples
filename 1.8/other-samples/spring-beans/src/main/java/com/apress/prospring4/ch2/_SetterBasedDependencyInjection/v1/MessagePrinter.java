package com.apress.prospring4.ch2._SetterBasedDependencyInjection.v1;

import com.apress.prospring4.ch2._SetterBasedDependencyInjection.v1.service.MessageService;

public class MessagePrinter {

  final private MessageService service;

  public MessagePrinter(MessageService service) {
    this.service = service;
  }

  public void printMessage() {
    System.out.println(this.service.getMessage());
  }
}