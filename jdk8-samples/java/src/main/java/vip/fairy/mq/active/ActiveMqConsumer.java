package vip.fairy.mq.active;

import java.util.concurrent.atomic.AtomicInteger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMqConsumer {

  private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

  private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

  private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

  ConnectionFactory connectionFactory;

  Connection connection;

  Session session;

  ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<>();
  AtomicInteger count = new AtomicInteger();

  public void init() {
    try {
      connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
      connection = connectionFactory.createConnection();
      connection.start();
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }


  public void getMessage(String disname) {
    try {
      Queue queue = session.createQueue(disname);
      MessageConsumer consumer = null;

      if (threadLocal.get() != null) {
        consumer = threadLocal.get();
      } else {
        consumer = session.createConsumer(queue);
        threadLocal.set(consumer);
      }
      while (true) {
        Thread.sleep(1000);
        TextMessage msg = (TextMessage) consumer.receive();
        if (msg != null) {
          msg.acknowledge();
          System.out.println(
              Thread.currentThread().getName() + ": Consumer:我是消费者，我正在消费Msg" + msg.getText() + "--->" + count.getAndIncrement());
        } else {
          break;
        }
      }
    } catch (JMSException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
