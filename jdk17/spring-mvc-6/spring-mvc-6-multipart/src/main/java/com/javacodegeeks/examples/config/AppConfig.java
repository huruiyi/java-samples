package com.javacodegeeks.examples.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.lang.reflect.Field;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.javacodegeeks.examples")
@PropertySource("classpath:application.properties")
public class AppConfig implements WebMvcConfigurer, WebApplicationInitializer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("WEB-INF/resources/");
  }

  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
  }


  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    try {
      // 通过反射获取嵌入式 Tomcat 的连接器
      Field contextField = servletContext.getClass().getDeclaredField("context");
      contextField.setAccessible(true);
      Object context = contextField.get(servletContext);

      Field serviceField = context.getClass().getDeclaredField("service");
      serviceField.setAccessible(true);
      Object service = serviceField.get(context);

      // 遍历所有连接器，设置 maxSwallowSize
      for (Object connector : (Object[]) service.getClass().getMethod("findConnectors").invoke(service)) {
        Object protocolHandler = connector.getClass().getMethod("getProtocolHandler").invoke(connector);

        // 检查是否为 HTTP/1.1 协议处理器
        if (protocolHandler.getClass().getName().contains("Http11")) {
          // 设置 maxSwallowSize 为 200MB
          protocolHandler.getClass().getMethod("setMaxSwallowSize", int.class).invoke(protocolHandler, -1);
        }
      }
    } catch (Exception e) {
      throw new ServletException("Failed to configure Tomcat maxSwallowSize", e);
    }
  }
}
