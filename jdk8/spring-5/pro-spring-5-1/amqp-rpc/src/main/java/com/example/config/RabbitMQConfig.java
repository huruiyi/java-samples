package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.example")
@EnableRabbit
public class RabbitMQConfig {

 public static final String queueName = "forecasts";
 public static final String exchangeName = "weather";

  @Bean
  ConnectionFactory connectionFactory() {
    CachingConnectionFactory con = new CachingConnectionFactory();
    con.setUsername("admin");
    con.setPassword("admin");
    con.setVirtualHost("/");
    return con;
  }

  @Bean
  RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate();
    rabbitTemplate.setConnectionFactory(connectionFactory);
    rabbitTemplate.setReplyTimeout(2000);
    rabbitTemplate.setRoutingKey(queueName);
    rabbitTemplate.setExchange(exchangeName);
    return rabbitTemplate;
  }

  @Bean
  Queue forecasts() {
    return new Queue(queueName, true);
  }

  @Bean
  Binding dataBinding(DirectExchange directExchange, Queue queue) {
    return BindingBuilder.bind(queue).to(directExchange).with(queueName);
  }

  @Bean
  RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
    RabbitAdmin admin = new RabbitAdmin(connectionFactory);
    admin.declareQueue(forecasts());
    admin.declareBinding(dataBinding(weather(), forecasts()));
    admin.setAutoStartup(true);
    return admin;
  }

  @Bean
  DirectExchange weather() {
    return new DirectExchange(exchangeName, true, false);
  }

  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory());
    factory.setMaxConcurrentConsumers(5);
    return factory;
  }

}
