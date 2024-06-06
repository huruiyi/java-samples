package com.example.mq.active;

public class ActiveMqConsumerTest {

  public static void main(String[] args) {
    ActiveMqConsumer consumer = new ActiveMqConsumer();
    consumer.init();
    ActiveMqConsumerTest testConsumer = new ActiveMqConsumerTest();
    new Thread(testConsumer.new ConsumerMq(consumer)).start();
    new Thread(testConsumer.new ConsumerMq(consumer)).start();
    new Thread(testConsumer.new ConsumerMq(consumer)).start();
    new Thread(testConsumer.new ConsumerMq(consumer)).start();
  }

  private class ConsumerMq implements Runnable {

    ActiveMqConsumer consumer;

    public ConsumerMq(ActiveMqConsumer consumer) {
      this.consumer = consumer;
    }

    @Override
    public void run() {
      while (true) {
        try {
          consumer.getMessage("Jaycekon-MQ");
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

}
