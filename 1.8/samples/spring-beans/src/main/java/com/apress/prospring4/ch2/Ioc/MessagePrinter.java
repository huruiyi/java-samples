package com.apress.prospring4.ch2.Ioc;

import com.apress.prospring4.ch2.Ioc.service.MessageService;
import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {

  private final MessageService service;

  public MessagePrinter(MessageService service) {
    this.service = service;
  }

//    @Autowired
//    private MessageService service;

  public void printMessage() {
    System.out.println(this.service.getMessage());
  }
}