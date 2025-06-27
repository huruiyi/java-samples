package com.javacodegeeks.examples.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PropertySource("classpath:application.properties")
public class AppController implements HandlerExceptionResolver {

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

  @RequestMapping("/upload")
  public String uploadForm() {
    return "upload";
  }

  @RequestMapping("/error")
  public String error() {
    return "error";
  }

  @GetMapping("/")
  public String index() {
    return "redirect:/upload";
  }

  @PostMapping("/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) throws IOException {
    String rootDirectory = request.getSession().getServletContext().getRealPath("/");
    Path path = Paths.get(rootDirectory + fileDirectory + file.getOriginalFilename());
    file.transferTo(new File(path.toString()));
    model.addAttribute("filename", file.getOriginalFilename());
    return "success";
  }

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    Map<String, Object> model = new HashMap<>();
    if (ex instanceof MaxUploadSizeExceededException) {
      model.put("errors", ex.getMessage());
    } else {
      model.put("errors", "Unexpected error: " + ex.getMessage());
    }
    return new ModelAndView("/error", model);
  }


}
