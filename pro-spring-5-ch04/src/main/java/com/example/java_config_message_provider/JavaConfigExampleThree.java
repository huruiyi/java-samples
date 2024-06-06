package com.example.java_config_message_provider;

import com.example.java_config_message_provider.model.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigExampleThree {

  public static void main(String... args) {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/java-config-message-provider-context-02.xml");
    MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
    renderer.render();
  }

}
