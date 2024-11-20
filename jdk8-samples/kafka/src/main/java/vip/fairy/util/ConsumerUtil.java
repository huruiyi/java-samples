package vip.fairy.util;

import vip.fairy.dbhelper.DBHelper;
import vip.fairy.pojo.Info;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.log4j.Logger;

public class ConsumerUtil {

  private static final Logger logger = Logger.getLogger(ConsumerUtil.class);

  private static final String TOPIC = "topic-demo";

  private static final ConsumerConnector connector;

  private static final Map<String, Integer> topicCountMap;

  static {
    Properties properties = new Properties();
    properties.put("zookeeper.connect", "localhost:2181");
    properties.put("group.id", "test-group");// 必须要使用别的组名称， 如果生产者和消费者都在同一组，则不能访问同一组内的topic数据

    ConsumerConfig conf = new ConsumerConfig(properties);
    connector = Consumer.createJavaConsumerConnector(conf);
    topicCountMap = new HashMap<>();
  }

  public static void consumer() {
    topicCountMap.put(TOPIC, 1); // 一次从主题中获取一个数据
    Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = connector.createMessageStreams(topicCountMap);
    KafkaStream<byte[], byte[]> stream = messageStreams.get(TOPIC).get(0);// 获取每次接收到的这个数据
    ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
    while (iterator.hasNext()) {
      String message = new String(iterator.next().message());
      String topic = iterator.next().topic();

      logger.info("************************************************************************************************************");
      logger.info("接收到: " + message);
      logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "消费者消费了一条消息------------");
      logger.info("************************************************************************************************************\n");

      Info i = new Info();
      i.setId(UUID.randomUUID().toString().replaceAll("-", ""));
      i.setMessage(message);
      i.setTopic(topic);
      insertInfo(i);

    }
  }

  private static void insertInfo(Info i) {
    String sql = "insert into t_info(id,topic, message,insert_date) values(?,?,?,now());";
    Object[] values = {i.getId(), i.getTopic(), i.getMessage()};
    DBHelper.insert(sql, values);
  }

}
