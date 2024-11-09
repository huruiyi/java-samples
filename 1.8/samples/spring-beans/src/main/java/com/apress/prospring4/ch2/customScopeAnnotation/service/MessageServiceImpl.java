package com.apress.prospring4.ch2.customScopeAnnotation.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("threadScope")
@Service
public class MessageServiceImpl implements MessageService {

  public String getMessage() {
    return "Hello World!";
  }

}
