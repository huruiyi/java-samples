package com.example.java_config_message_provider;

import com.example.java_config_message_provider.model.MessageProvider;
import com.example.java_config_message_provider.model.MessageRenderer;
import com.example.java_config_message_provider.model.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:message.properties")
public class AppConfigOne {

  final Environment env;

  public AppConfigOne(Environment env) {
    this.env = env;
  }

  @Bean
  @Lazy
  public MessageProvider messageProvider() {
    return new ConfigurableMessageProvider(env.getProperty("message"));
  }

  @Bean(name = "messageRenderer")
  @Scope(value = "prototype")
  @DependsOn(value = "messageProvider")
  public MessageRenderer messageRenderer() {
    MessageRenderer renderer = new StandardOutMessageRenderer();
    renderer.setMessageProvider(messageProvider());
    return renderer;
  }
}
