package com.roy.service.impl;

import com.roy.service.HelloService;

public class MorningHello implements HelloService {

  @Override
  public String sayHello(String name) {
    return "good morning " + name;
  }

}
