package com.javacodegeeks.examples.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import java.io.File;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  private File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));


  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{AppConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return null;
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  private MultipartConfigElement getMultipartConfigElement() {
    String absolutePath = uploadDirectory.getAbsolutePath();
    return new MultipartConfigElement(absolutePath,
        1024 * 1024 * 100L /*100MB*/,
        1024 * 1024 * 200L /*200MB*/,
        1024 * 1024 * 50 /*50MB*/);
  }

  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    MultipartConfigElement multipartConfigElement = getMultipartConfigElement();
    registration.setMultipartConfig(multipartConfigElement);
  }


}
