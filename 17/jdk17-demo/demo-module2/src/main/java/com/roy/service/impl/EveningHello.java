package com.roy.service.impl;

import com.roy.service.HelloService;

public class EveningHello implements HelloService {

  @Override
  public String sayHello(String name) {
    return "good evening " + name;
  }

}
