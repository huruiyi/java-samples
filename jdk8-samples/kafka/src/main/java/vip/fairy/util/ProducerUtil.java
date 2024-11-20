package vip.fairy.util;

import java.util.Properties;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerUtil {

  private static final String TOPIC = "topic-demo";
  private static final String BROKER_LISTS = "localhost:9092";
  public static KeyedMessage<String, String> message = null;
  private static final Producer<String, String> producer;

  static {
    Properties properties = new Properties();
    properties.put("serializer.class", "kafka.serializer.StringEncoder");
    properties.put("metadata.broker.list", BROKER_LISTS);

    ProducerConfig config = new ProducerConfig(properties);
    producer = new kafka.javaapi.producer.Producer<>(config);
  }

  public static void producer(Object msg) {
    message = new KeyedMessage<>(TOPIC, "message : " + msg);
    producer.send(message);
    System.out.println("send message : " + msg);
  }


}
