package com.example.demo.controller;

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

}
