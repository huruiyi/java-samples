package com.example.mq.active;

public class ActiveMqProducerTest {

  public static void main(String[] args) {
    ActiveMqProducer producer = new ActiveMqProducer();
    producer.init();
    ActiveMqProducerTest testMq = new ActiveMqProducerTest();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    new Thread(testMq.new ProducerMq(producer)).start();
    new Thread(testMq.new ProducerMq(producer)).start();
    new Thread(testMq.new ProducerMq(producer)).start();
    new Thread(testMq.new ProducerMq(producer)).start();
    new Thread(testMq.new ProducerMq(producer)).start();
  }

  private class ProducerMq implements Runnable {

    ActiveMqProducer producer;

    public ProducerMq(ActiveMqProducer producer) {
      this.producer = producer;
    }

    @Override
    public void run() {
      while (true) {
        try {
          producer.sendMessage("Jaycekon-MQ");
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
