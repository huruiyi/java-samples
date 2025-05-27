package com.example.web;

import com.example.service.HelloService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorld {

  Logger logger = Logger.getLogger(HelloWorld.class);

  @Autowired
  HelloService helloService;

  @RequestMapping
  public String index(Model model) {
    logger.info("Hello World,世界你好！！！！！！");
    helloService.sayHi("da ye");
    return "index";
  }

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello(Model model) {
    model.addAttribute("msg", "SpringMvc HelloWorld例子演示成功啦...");
    return "hello";
  }

}
