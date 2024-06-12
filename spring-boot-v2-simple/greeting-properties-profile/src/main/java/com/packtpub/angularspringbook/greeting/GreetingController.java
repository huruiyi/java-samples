package com.packtpub.angularspringbook.greeting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greeting")
class GreetingController {

  private final GreetingProperties greetingProperties;

  public GreetingController(GreetingProperties greetingProperties) {
    this.greetingProperties = greetingProperties;
  }

  @GetMapping(value = "/v1")
  public String sayHello(@Value("${greeting.message}") String message, @RequestParam("name") String name) {
    return String.format(message, name);
  }

  @GetMapping(value = "/v2")
  public String sayHello(@RequestParam("name") String name) {
    return String.format(greetingProperties.getMessage(), name);
  }

}
