package com.example.mq.rabbit;

import com.rabbitmq.client.ConfirmListener;
import java.io.IOException;

public class RabbitMqConfirmListener implements ConfirmListener {

  @Override
  public void handleAck(long deliveryTag, boolean multiple) throws IOException {
    System.out.println("handleAck()    deliveryTag :" + deliveryTag + ",multiple :" + multiple);
  }

  @Override
  public void handleNack(long deliveryTag, boolean multiple) throws IOException {
    System.out.println("handleNack()   deliveryTag :" + deliveryTag + ",multiple :" + multiple);

  }
}
