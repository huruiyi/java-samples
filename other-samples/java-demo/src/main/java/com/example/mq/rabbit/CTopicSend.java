package com.example.mq.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;

public class CTopicSend {

  private static final String EXCHANGE_NAME = "topic_logs";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    RabbitMqReturnListener returnListener = new RabbitMqReturnListener();
    channel.addReturnListener(returnListener);

    AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
    properties.deliveryMode(2);//持久化
    properties.expiration("5");
    AMQP.BasicProperties basicProperties = properties.build();

    new Thread(new Runner(channel, "key:admin.info")).start();
    new Thread(new Runner(channel, "key:admin.debug")).start();
    new Thread(new Runner(channel, "key:admin.warn", basicProperties)).start();
    //消息不会被接受
    new Thread(new Runner(channel, "key:anonymous.error", basicProperties)).start();
  }

  public static class Runner implements Runnable {

    Channel channel;
    String routingKey;
    AMQP.BasicProperties properties;

    public Runner(Channel channel, String routingKey, AMQP.BasicProperties properties) {
      this.channel = channel;
      this.routingKey = routingKey;
      this.properties = properties;
    }

    public Runner(Channel channel, String routingKey) {
      this.channel = channel;
      this.routingKey = routingKey;
    }

    @Override
    public void run() {
      try {
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        for (int i = 0; i < 100000; i++) {
          Thread.sleep(2000);
          String message = "Hello World" + " " + i;

          // example 1
          // channel.basicPublish(EXCHANGE_NAME, routingKey, properties, message.getBytes("UTF-8"));

          // example 2
          channel.basicPublish(EXCHANGE_NAME, routingKey, true, false, properties, message.getBytes("UTF-8"));
          System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        }
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      //String routingKey = "";
    }
  }


}
