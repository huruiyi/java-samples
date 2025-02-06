package vip.fairy;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  static final String queueName = "forecasts";
  static final String exchangeName = "weather";

  @Bean
  Queue forecasts() {
    return new Queue(queueName, true);
  }

  @Bean
  DirectExchange weather() {
    return new DirectExchange(exchangeName, true, false);
  }

  @Bean
  Binding dataBinding(DirectExchange directExchange, Queue queue) {
    return BindingBuilder.bind(queue).to(directExchange).with(queueName);
  }

  @Bean
  CachingConnectionFactory connectionFactory() {
    return new CachingConnectionFactory("127.0.0.1");
  }

  @Bean
  SimpleMessageListenerContainer messageListenerContainer() {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory());
    container.setQueueNames(queueName);
    return container;
  }

  public static void main(String... args) throws Exception {
    ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

    RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
    rabbitTemplate.convertAndSend(Application.queueName, "FL");
    rabbitTemplate.convertAndSend(Application.queueName, "MA");
    rabbitTemplate.convertAndSend(Application.queueName, "CA");

    System.in.read();
    ctx.close();
  }
}
