package com.apress.prospring4.ch2.SpringAnnotation.service.impl;

import com.apress.prospring4.ch2.SpringAnnotation.annotation.RpcService;
import com.apress.prospring4.ch2.SpringAnnotation.service.HelloService;

@RpcService("HelloService")
public class HelloServiceImpl implements HelloService {

  public String hello(String name) {
    return "Hello! " + name;
  }

}
