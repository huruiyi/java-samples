package com.example.config;

import java.util.HashMap;
import java.util.Map;
import javax.jms.ConnectionFactory;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.core.remoting.impl.netty.TransportConstants;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;
import org.hornetq.jms.client.HornetQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;


@Configuration
@EnableJms
@ComponentScan("com.example")
public class AppConfig {

  @Bean
  HornetQQueue queue() {
    return new HornetQQueue("prospring5");
  }

  @Bean
  ConnectionFactory connectionFactory() {
    Map<String, Object> connDetails = new HashMap<>();
    connDetails.put(TransportConstants.HOST_PROP_NAME, "127.0.0.1");
    connDetails.put(TransportConstants.PORT_PROP_NAME, "5445");
    TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName(), connDetails);
    return new HornetQJMSConnectionFactory(false, transportConfiguration);
  }

  @Bean
  public JmsListenerContainerFactory<DefaultMessageListenerContainer> jmsListenerContainerFactory() {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory());
    factory.setConcurrency("3-5");
    return factory;
  }

  @Bean
  JmsTemplate jmsTemplate() {
    JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
    jmsTemplate.setDefaultDestination(queue());
    return jmsTemplate;
  }
}
