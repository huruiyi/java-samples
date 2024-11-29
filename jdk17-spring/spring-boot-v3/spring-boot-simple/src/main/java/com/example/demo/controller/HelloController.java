package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    @GetMapping(value = {"/", "/hello"}, produces = "text/json;charset=utf-8")
//    public String hello() {
//        return "你好，hello ";
//    }

  @GetMapping(value = {"/", "/hello"})
  public String hello() {
    return "你好，hello ";
  }


  @GetMapping("/greetings/{name}")
  public ResponseEntity<Greeting> greetings(@PathVariable("name") String name) throws IllegalAccessException {
    if (!StringUtils.hasText(name) || !Character.isUpperCase(name.charAt(0))) {
      throw new IllegalArgumentException("name must is not starting with capital letter ");
    }
    return ResponseEntity.ok(new Greeting("Hello " + name + "!"));
  }


}
