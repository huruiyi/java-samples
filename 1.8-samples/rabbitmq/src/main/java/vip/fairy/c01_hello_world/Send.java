package vip.fairy.c01_hello_world;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.nio.charset.StandardCharsets;

public class Send {

  private final static String QUEUE_NAME = "hello";

  public static void main(String[] args) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
      //queue – the name of the queue
      //durable – true if we are declaring a durable queue (the queue will survive a server restart)
      //exclusive – true if we are declaring an exclusive queue (restricted to this connection)
      //autoDelete – true if we are declaring an autodelete queue (server will delete it when no longer in use)
      //arguments – other properties (construction arguments) for the queue
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      for (int i = 1; i <= 20; i++) {
        String message = "Hello World!".concat("-").concat(String.valueOf(i));
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println(" [x] Sent '" + message + "'");
      }

    }

  }
}
