package com.example.mq.rabbit;

import com.rabbitmq.client.ConfirmCallback;
import java.io.IOException;

public class RabbitMqConfirmCallback implements ConfirmCallback {

  @Override
  public void handle(long deliveryTag, boolean multiple) throws IOException {
    System.out.println();
  }
}
