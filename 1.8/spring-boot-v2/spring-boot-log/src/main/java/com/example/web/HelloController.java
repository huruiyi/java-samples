package com.example.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

  @RequestMapping(value = {"/", ""})
  public String hello() {
    for (int i = 0; i < 100000; i++) {
      log.info("Hello World:世界你好！！{}", i);
    }
    return "Hello World";
  }
}
