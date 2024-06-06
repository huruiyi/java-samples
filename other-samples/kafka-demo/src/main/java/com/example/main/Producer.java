package com.example.main;

import com.example.util.ProducerUtil;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

public class Producer {

  private static final Logger logger = Logger.getLogger(Producer.class);

  public static void main(String[] args) {
    System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    new Thread(() -> {
      while (true) {
        try {
          TimeUnit.SECONDS.sleep(1);
          logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "生产者生产了一条消息------------");
          ProducerUtil.producer("my kafka send message : " + (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }).start();
  }
}
