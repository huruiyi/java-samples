package com.example.java_config_message_provider;


import com.example.java_config_message_provider.model.MessageProvider;
import com.example.java_config_message_provider.model.MessageRenderer;
import com.example.java_config_message_provider.model.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.example.java_config_message_provider.annotated"})
public class AppConfigTwo {

  final MessageProvider messageProvider;

  public AppConfigTwo(MessageProvider messageProvider) {
    this.messageProvider = messageProvider;
  }


  @Bean(name = "messageRenderer")
  public MessageRenderer messageRenderer() {
    MessageRenderer renderer = new StandardOutMessageRenderer();
    renderer.setMessageProvider(messageProvider);
    return renderer;
  }
}
