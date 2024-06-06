package com.apress.prospring4.ch2.Ioc.service;

import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

  public String getMessage() {
    return "Hello World!";
  }

}
