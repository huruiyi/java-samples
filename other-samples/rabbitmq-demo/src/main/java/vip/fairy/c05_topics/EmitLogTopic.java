package vip.fairy.c05_topics;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.nio.charset.StandardCharsets;

public class EmitLogTopic {

  private static final String EXCHANGE_NAME = "topic_logs";

  /**
   * @param argv :"kern.critical" "A critical kernel error"
   */
  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {

      channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

      String message1 = "1:a error";
      channel.basicPublish(EXCHANGE_NAME, "kern.critical", null, message1.getBytes(StandardCharsets.UTF_8));

      String message2 = "2:a orange";
      channel.basicPublish(EXCHANGE_NAME, "kern.critical", null, message2.getBytes(StandardCharsets.UTF_8));

      String message3 = "3:a orange";
      channel.basicPublish(EXCHANGE_NAME, "fruit.orange", null, message3.getBytes(StandardCharsets.UTF_8));

      String message4 = "3:a apple";
      channel.basicPublish(EXCHANGE_NAME, "fruit.apple", null, message4.getBytes(StandardCharsets.UTF_8));
    }
  }

}

