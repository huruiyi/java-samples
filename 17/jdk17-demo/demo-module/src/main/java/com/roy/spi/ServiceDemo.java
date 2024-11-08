package com.roy.spi;

import com.roy.service.HelloService;

import java.util.ServiceLoader;

public class ServiceDemo {

  public static void main(String[] args) {
    ServiceLoader<HelloService> services = ServiceLoader.load(HelloService.class);
    for (HelloService service : services) {
      System.out.println(service.sayHello("loulan"));
    }
  }

}
