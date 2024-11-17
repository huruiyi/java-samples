package com.example.java_config;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class JavaConfigSimpleDemo {

  @Configuration
  static class AppConfig {

    @Bean
    public MessageProvider messageProvider() {
      return new ConfigurableMessageProvider();
    }

    @Bean
    public MessageRenderer messageRenderer() {
      MessageRenderer renderer = new StandardOutMessageRenderer();
      renderer.setMessageProvider(messageProvider());
      return renderer;
    }
  }


  public static void main(String... args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
    renderer.render();
  }
}
