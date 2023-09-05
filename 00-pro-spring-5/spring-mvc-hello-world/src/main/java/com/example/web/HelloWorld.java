package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorld {

    @RequestMapping
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/springmvc/helloworld", method = RequestMethod.GET)
    public String HelloWorld(Model model) {
        model.addAttribute("msg", "SpringMvc HelloWorld例子演示成功啦...");
        return "helloworld";
    }
}
