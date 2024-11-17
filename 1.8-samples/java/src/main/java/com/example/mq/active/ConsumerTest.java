package com.example.mq.active;

import java.io.IOException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;

public class ConsumerTest {

  /**
   * 测试Queue 消费者：接收消息
   *
   * @throws JMSException
   * @throws IOException
   */
  @Test
  public void testQueueConsumer() throws JMSException, IOException {
    // 1）创建一个 ConnectionFactory 对象。
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
    // 2）从 ConnectionFactory 对象中获得一个 Connection 对象。
    Connection connection = connectionFactory.createConnection();
    // 3）开启连接。调用 Connection 对象的 start 方法。
    connection.start();
    // 4）使用 Connection 对象创建一个 Session 对象。
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    // 5）使用 Session 对象创建一个 Destination 对象。和发送端保持一致 queue，并且队列的名称一致。
    Queue queue = session.createQueue("test-queue");
    // 6）使用 Session 对象创建一个 Consumer 对象。
    MessageConsumer consumer = session.createConsumer(queue);
    // 7）接收消息。
    consumer.setMessageListener(new ConsumerMessageListener());
    // 等待键盘输入
    System.in.read();
    // 9）关闭资源
    consumer.close();
    session.close();
    connection.close();
  }

  /**
   * 测试Topic 消费者：接收消息
   */
  @Test
  public void testTopicCusumer() throws JMSException, IOException {
    // 1）创建一个 ConnectionFactory 对象。
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
    // 2）从 ConnectionFactory 对象中获得一个 Connection 对象。参数用户名/密码
    Connection connection = connectionFactory.createConnection();
    // 3）开启连接。调用 Connection 对象的 start 方法。
    connection.start();
    // 4）使用 Connection 对象创建一个 Session 对象。
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    // 5）使用 Session 对象创建一个 Destination 对象。和发送端保持一致 topic，并且话题的名称一致。
    Topic topic = session.createTopic("test-topic");
    // 6）使用 Session 对象创建一个 Consumer 对象。
    MessageConsumer consumer = session.createConsumer(topic);

    consumer.setMessageListener(new ConsumerMessageListener());
    System.out.println("topic 的消费端............");

    System.in.read();

    consumer.close();
    session.close();
    connection.close();
  }


}
