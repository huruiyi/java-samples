package com.example.java_config_message_provider.mixed;

import com.example.java_config_message_provider.model.MessageProvider;
import com.example.java_config_message_provider.model.MessageRenderer;
import com.example.java_config_message_provider.model.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = "classpath:spring/java-config-message-provider-context-01.xml")
public class AppConfigFive {

  final MessageProvider provider;

  public AppConfigFive(MessageProvider provider) {
    this.provider = provider;
  }


  @Bean(name = "messageRenderer")
  public MessageRenderer messageRenderer() {
    MessageRenderer renderer = new StandardOutMessageRenderer();
    renderer.setMessageProvider(provider);
    return renderer;
  }

}
