package com.packtpub.angularspringbook.greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
public class GreetingController {

  @GetMapping
  public String sayHello() {
    return "Hello Spring Boot";
  }

}
