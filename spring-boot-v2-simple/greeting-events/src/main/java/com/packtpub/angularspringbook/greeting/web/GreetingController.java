package com.packtpub.angularspringbook.greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GreetingController {

  @GetMapping("/demo1")
  public String sayHello() {
    return "Hello Spring Boot";
  }

  @GetMapping("/demo2")
  public Person showInfo() {
    return Person.builder().name("ni da ye").build();
  }
}
