package com.javacodegeeks.examples.exception;

import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MultipartExceptionHandler {

  // 处理 Spring 框架的文件大小超限异常
  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ModelAndView handleUploadExceptions(MaxUploadSizeExceededException ex) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("upload");
    // 可以选择性地添加错误消息
    modelAndView.addObject("error", "文件大小超出限制! "  );
    return modelAndView;
  }

  // 处理 Tomcat 的请求体过大异常
  @ExceptionHandler(ServletException.class)
  @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
  public String handleServletException(ServletException ex) {
    // 检查是否由请求体过大引起
    if (ex.getMessage() != null && ex.getMessage().contains("Request body too large")) {
      return "请求体过大! 请上传较小的文件。";
    }
    // 其他 Servlet 异常处理
    return "服务器处理请求时发生错误: " + ex.getMessage();
  }

}
