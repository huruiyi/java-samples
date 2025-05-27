package vip.fairy;

import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class RedisTest {

  public int arrayLength = 20000;
  public String ip = "127.0.0.1";
  public int port = 6379;
  public String[] keys = new String[arrayLength / 2];

  public boolean delNoPipe(String[] keys) {
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    for (String key : keys) {
      jedis.del(key);
    }
    jedis.close();
    return true;
  }

  public boolean delByPipe(String[] keys) {
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    Pipeline pipe = jedis.pipelined();
    for (String key : keys) {
      pipe.del(key); //del拼装
    }
    pipe.sync();//执行发送指令
    jedis.close();
    return true;
  }

  public void initRedisData() {
    Jedis jedis = new Jedis(ip, port);
    String[] str = new String[arrayLength]; //20000

    int j = 0;
    for (int i = 0; i < str.length / 2; i++) {
      str[j] = "key:" + i;
      str[j + 1] = "v" + i;
      j = j + 2;
      keys[i] = "key:" + i;
    }
    jedis.mset(str);
    jedis.close();
  }

  @Test
  public void delKeyTest() {
    initRedisData();//初始化数据  10000
    long t = System.currentTimeMillis();
    boolean a = delNoPipe(keys);//del
    Assert.assertTrue(a);
    System.out.println(System.currentTimeMillis() - t);

    initRedisData();//初始化数据  10000
    long t2 = System.currentTimeMillis();
    boolean b = delByPipe(keys);
    Assert.assertTrue(b);
    System.out.println(System.currentTimeMillis() - t2);
  }


}
