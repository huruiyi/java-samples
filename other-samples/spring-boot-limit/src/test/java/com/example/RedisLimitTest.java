package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisLimitTest {

  @Autowired
  private RedisTemplate redisTemplate;

  /**
   * 方式一：滑动窗口 实现原理：利用zset的score值来圈定这个时间窗口。每次请求过来，都会使用zset的范围查询统计总数， 这个范围就是（当前时间）到（当前时间-滑动窗口时间），然后将总数和限流次数进行比较，从而决定是否限流
   *
   * @param redisKey
   * @return
   */
  public boolean limitFlow(String redisKey) {
    int targetNum = 5; //限流次数
    long intervalTime = 60 * 1000; //时间窗口为60秒
    long currentTime = System.currentTimeMillis();
    //查询当前时间往前找 intervalTime限流时间这个范围有多少个数据
    Integer count = redisTemplate.opsForZSet().rangeByScore(redisKey, currentTime - intervalTime, currentTime).size();
    System.out.println(count + "-" + targetNum);
    if (count != null && count > targetNum) {
      //超出限流范围
      return false;
    }
    //没有超出限流范围 则将当前值放入zset中并返回成功
    redisTemplate.opsForZSet().add(redisKey, currentTime, currentTime);
    return true;
  }

  @Test
  public void Test_limitFlow() {
    long millis = System.currentTimeMillis();
    while (true) {
      boolean flag = limitFlow(String.valueOf(millis));
      if (!flag) {
        System.out.println("访问结束。。");
        break;
      }
    }

  }

  @Test
  public void Test1() {
    Calendar calendar = Calendar.getInstance();

    //1:时间戳
    long millis1 = calendar.getTimeInMillis();
    System.out.println(millis1);

    //2:时间戳
    long millis2 = System.currentTimeMillis();
    System.out.println(millis2);

    //3:时间戳
    long millis3 = new Date().getTime();
    System.out.println(millis3);

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");// 为获取当前系统时间，也可使用当前时间戳
    String date1 = df.format(new Date());
    System.out.println(date1);

    Date date2 = new Date(millis1);
    System.out.println(df.format(date2));

    calendar.setTimeInMillis(millis2 - 24 * 60 * 60 * 1000);
    Date date3 = calendar.getTime();
    System.out.println(df.format(date3));

    //时间还原
    calendar.setTimeInMillis(System.currentTimeMillis());
    Date date4 = calendar.getTime();
    System.out.println(df.format(date4));
  }
}
