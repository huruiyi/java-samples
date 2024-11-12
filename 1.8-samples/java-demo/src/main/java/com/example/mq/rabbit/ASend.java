package com.example.mq.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ASend {

  private final static String QUEUE_NAME = "hello2";

  public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("127.0.0.1");

    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);

    RabbitMqReturnListener listener = new RabbitMqReturnListener();

    channel.addReturnListener(listener);

    for (int i = 0; i < 100000000; i++) {
      Thread.sleep(1000);
      String message = "Hello World! count: " + i;
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
      System.out.println(" [x] Sent '" + message + "'");

    }

  }
}
