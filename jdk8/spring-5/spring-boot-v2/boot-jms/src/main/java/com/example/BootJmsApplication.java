package com.example;

import java.io.IOException;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@SpringBootApplication
public class BootJmsApplication {

  private static final Logger logger = LoggerFactory.getLogger(BootJmsApplication.class);

  @Bean
  public JmsListenerContainerFactory<DefaultMessageListenerContainer> connectionFactory(ConnectionFactory connectionFactory,
      DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    configurer.configure(factory, connectionFactory);
    return factory;
  }

  public static void main(String... args) throws IOException {
    ConfigurableApplicationContext ctx = SpringApplication.run(BootJmsApplication.class, args);
    JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
    jmsTemplate.setDeliveryDelay(5000L);
    for (int i = 0; i < 10; ++i) {
      logger.info(">>> Sending: Test message: {}", i);
      jmsTemplate.convertAndSend("prospring5", "Test message: " + i);
    }

    System.in.read();
    ctx.close();
  }

  @JmsListener(destination = "prospring5", containerFactory = "connectionFactory")
  public void onMessage(Message message) {
    TextMessage textMessage = (TextMessage) message;
    try {
      logger.info(">>> Received: {}", textMessage.getText());
    } catch (JMSException ex) {
      logger.error("JMS error", ex);
    }
  }


}
