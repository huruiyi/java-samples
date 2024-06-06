package com.apress.prospring4.ch2.Hello2.service;

import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

  public String getMessage() {
    return "Hello World，世界你好!";
  }

}
