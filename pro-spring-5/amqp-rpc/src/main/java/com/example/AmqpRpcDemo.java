package com.example;

import com.example.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class AmqpRpcDemo {

  public static void main(String... args) throws Exception {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
    RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
    rabbitTemplate.convertAndSend("FL");
    rabbitTemplate.convertAndSend("MA");
    rabbitTemplate.convertAndSend("CA");

    ctx.close();
  }
}
