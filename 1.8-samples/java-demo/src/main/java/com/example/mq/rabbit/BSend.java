package com.example.mq.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.SerializationUtils;

public class BSend {

  private static final String EXCHANGE_NAME = "header_exchange";

  public static void sendAToB(String object) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.HEADERS);
    Map<String, Object> headers = new HashMap<String, Object>();
    headers.put("key", "123456");
    headers.put("token", "654321");

    AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
    properties.headers(headers);
    properties.deliveryMode(2);//持久化

    properties.expiration("22000");

    RabbitMqReturnListener returnListener = new RabbitMqReturnListener();
    channel.addReturnListener(returnListener);

    for (int i = 0; i < 1000000; i++) {
      Thread.sleep(2000);
      channel.basicPublish(EXCHANGE_NAME,
          "",
          true,
          false,
          properties.build(),
          SerializationUtils.serialize((object + i)));
      System.out.println("Send '" + (object + i) + "'");
    }


  }

  public static void main(String[] args) throws Exception {
    sendAToB("Hello World !");
  }

}
