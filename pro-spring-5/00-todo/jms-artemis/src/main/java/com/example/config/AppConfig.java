package com.example.config;


import java.util.HashMap;
import java.util.Map;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory;
import org.apache.activemq.artemis.core.remoting.impl.netty.TransportConstants;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.BeanFactoryDestinationResolver;

@Configuration
@EnableJms
@ComponentScan("com.example")
public class AppConfig {

  @Autowired
  private BeanFactory springContextBeanFactory;

  @Bean
  ActiveMQQueue prospring5() {
    return new ActiveMQQueue("/queue/prospring5");
  }


  @Value("${spring.artemis.broker-url}")
  private String brokerUrl;


  @Value("${spring.artemis.user}")
  private String username;

  @Value("${spring.artemis.password}")
  private String password;

  @Bean
  public ConnectionFactory connectionFactory() throws Exception {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setBrokerURL("tcp://localhost:61617");
    connectionFactory.setUser("admin");
    connectionFactory.setPassword("admin");
    return connectionFactory;
  }

//  @Bean
//  ActiveMQConnectionFactory connectionFactory() {
//    Map<String, Object> connDetails = new HashMap<>();
//    connDetails.put(TransportConstants.HOST_PROP_NAME, "127.0.0.1");
//    connDetails.put(TransportConstants.PORT_PROP_NAME, "61617");
//    connDetails.put(TransportConstants.PROTOCOLS_PROP_NAME, "tcp");
//    TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName(), connDetails);
//    return new ActiveMQConnectionFactory(false, transportConfiguration);
//  }


  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws Exception {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory());
    factory.setDestinationResolver(new BeanFactoryDestinationResolver(springContextBeanFactory));
    factory.setConcurrency("3-10");

    return factory;
  }

  @Bean
  JmsTemplate jmsTemplate() throws Exception {
    JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
    jmsTemplate.setDefaultDestination(prospring5());
    return jmsTemplate;
  }



}
