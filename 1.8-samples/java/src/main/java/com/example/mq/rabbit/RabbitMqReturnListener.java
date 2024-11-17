package com.example.mq.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ReturnListener;
import java.io.IOException;

public class RabbitMqReturnListener implements ReturnListener {

  @Override
  public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body)
      throws IOException {
    System.out.println("replyCode : " + replyCode + "  replyText ：" + replyText + "  exchange ：" + exchange + "  routingKey ：" + routingKey);
  }
}
