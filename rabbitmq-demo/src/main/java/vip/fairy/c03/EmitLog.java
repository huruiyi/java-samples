package vip.fairy.c03;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class EmitLog {


  private static final String EXCHANGE_NAME = "logs";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
      channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

      for (int i = 1; i <= 30; i++) {
        TimeUnit.SECONDS.sleep(1);
        String message = String.valueOf(i).concat("-").concat("info: Hello World!");
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println(" [x] Sent '" + message + "'");
      }

    }
  }


}
