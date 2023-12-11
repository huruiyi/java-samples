package com.javacodegeeks.examples.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@PropertySource("classpath:application.properties")
public class AppController {

  @Value("${file.directory}")
  private String fileDirectory;

  @ResponseBody
  @GetMapping("/hello")
  public String hello() {
    return "Hello World";
  }

  @ResponseBody
  @GetMapping("/exception")
  public String exception() {
    throw new MultipartException("MultipartException 。。。。。。。");
  }

  @GetMapping("/uploadForm")
  public String uploadForm() {
    return "upload";
  }

  @GetMapping("/")
  public String index() {
    return "redirect:/uploadForm";
  }

  @PostMapping("/uploadFile")
  public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) throws IOException {
    String rootDirectory = request.getSession().getServletContext().getRealPath("/");
    Path path = Paths.get(rootDirectory + fileDirectory + file.getOriginalFilename());
    file.transferTo(new File(path.toString()));
    model.addAttribute("filename", file.getOriginalFilename());
    return "success";
  }

}
