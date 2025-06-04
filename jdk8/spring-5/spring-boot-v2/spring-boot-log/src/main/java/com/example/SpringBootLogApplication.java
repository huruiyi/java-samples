package com.example;

import com.example.servlet.MyServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootLogApplication {

  @Bean
  public ServletRegistrationBean<MyServlet> myServlet() {
    ServletRegistrationBean<MyServlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
    servletServletRegistrationBean.setServlet(new MyServlet());
    servletServletRegistrationBean.addUrlMappings("/hello");
    return servletServletRegistrationBean;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringBootLogApplication.class, args);
  }

}
