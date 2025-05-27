package vip.fairy.consumers;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import vip.fairy.model.Person;

/**
 * {"name":"tn01","processedTimestamp":12345}
 */
@Configuration
public class NameSinkConfiguration {

  Logger log = LoggerFactory.getLogger(NameSinkConfiguration.class);

  @Bean
  public Consumer<Person> phoneMessage() {
    return person -> {
            log.info("phoneMessage-消息消费成功：  Payload:{}", person);
      System.out.println(person.getName() + "---" + person.getProcessedTimestamp());
    };
  }

  @Bean
  public Consumer<Message<Person>> mailMessage() {
    return person -> {
      Channel channel = person.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
      Long deliveryTag = person.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);
      log.info("mailMessage-消息消费成功：  Payload:{}", person.getPayload());
      try {
        channel.basicAck(deliveryTag, false);
      } catch (IOException e) {
        try {
          channel.basicAck(deliveryTag, true);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    };
  }

  @Bean
  public Consumer<Message<Person>> smsMessage() {
    log.info("smsMessage-初始化订阅");
    return obj -> {
      Channel channel = obj.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
      Long deliveryTag = obj.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);
      try {
        long currentTimeMillis = System.currentTimeMillis();
        if (channel != null && currentTimeMillis % 2 == 0) {
          log.info("smsMessage-消息消费成功：currentTimeMillis: {}, Payload:{}", currentTimeMillis, obj.getPayload());
          channel.basicAck(deliveryTag, false);
        } else {
          //丢弃
          log.info("smsMessage-消息消费失败：currentTimeMillis: {}, Payload:{}", currentTimeMillis, obj.getPayload());
          channel.basicReject(deliveryTag, true);
        }
      } catch (Exception e) {
        if (channel != null) {
          try {
            //重新回队列-true则重新入队列，否则丢弃或者进入死信队列。
            channel.basicReject(deliveryTag, true);
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }
        log.error(e.getMessage());
      }
    };
  }

}
