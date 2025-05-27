package vip.fairy.c04_routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class EmitLogDirect {

  private static final String EXCHANGE_NAME = "direct_logs";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
      channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

      String logLevel = getSeverity(argv);
      String message = getMessage(argv);

      for (int i = 1; i < 20; i++) {
        String msg = String.valueOf(i).concat("-").concat(message);
        channel.basicPublish(EXCHANGE_NAME, logLevel, null, msg.getBytes(StandardCharsets.UTF_8));
        System.out.println(" [x] Sent '" + logLevel + "':'" + msg + "'");
        TimeUnit.SECONDS.sleep(1);
      }
    }
  }

  private static String getSeverity(String[] strings) {
    if (strings.length < 1) {
      //[info] [warning] [error]
      return "warning";
    }
    return strings[0];
  }

  private static String getMessage(String[] strings) {
    if (strings.length < 2) {
      return "Hello World!";
    }
    return joinStrings(strings);
  }

  private static String joinStrings(String[] strings) {
    int length = strings.length;
    if (length == 0) {
      return "";
    }
    if (length <= 1) {
      return "";
    }
    StringBuilder words = new StringBuilder(strings[1]);
    for (int i = 1 + 1; i < length; i++) {
      words.append(" ").append(strings[i]);
    }
    return words.toString();
  }
}

