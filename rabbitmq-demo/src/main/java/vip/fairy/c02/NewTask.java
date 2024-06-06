package vip.fairy.c02;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import java.nio.charset.StandardCharsets;

public class NewTask {

 private static final String TASK_QUEUE_NAME = "task_queue";

  public static void main(String[] argv) throws Exception {
    argv=new String[3];
    argv[0]="localhost";
    argv[1]="task";
    argv[2]="queue";

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try (Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
      channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

      String message = String.join(" ", argv);

      channel.basicPublish("", TASK_QUEUE_NAME,
          MessageProperties.PERSISTENT_TEXT_PLAIN,
          message.getBytes(StandardCharsets.UTF_8));
      System.out.println(" [x] Sent '" + message + "'");
    }
  }

}
