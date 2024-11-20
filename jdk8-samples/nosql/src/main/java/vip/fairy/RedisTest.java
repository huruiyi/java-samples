package vip.fairy;

import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.args.ListPosition;

public class RedisTest {

  private static final Logger log = LoggerFactory.getLogger(RedisTest.class);

  private static final Jedis jedis;

  static {
    jedis = new Jedis("127.0.0.1",6379);
  }

  public static void main(String[] args) {
    Redis_Lsit1();
  }

  public static void Redis_Set_Get() {
    log.info("服务正在运行: " + jedis.ping());
    jedis.set("name", "jredis");
    log.info("jredis value:" + jedis.get("name"));
  }

  public static void Redis_Keys() {
    Set<String> keys = jedis.keys("*");
    for (String key : keys) {
      System.out.println(key);
    }
    System.out.println("****************************");
    for (String key : keys) {
      System.out.println(key);
    }
  }

  public static void Redis_Lsit1() {
    String key = "nations";
    jedis.del(key);
    jedis.lpush(key, "中国");
    jedis.lpush(key, "俄国");
    jedis.lpush(key, "美国");
    jedis.lpush(key, "日国");
    jedis.lpush(key, "韩国");
    long len = jedis.llen(key);

    System.out.println("nations list length:" + len);
    List<String> list = jedis.lrange(key, 0, len - 1);
    for (String nation : list) {
      System.out.print(nation + " ");
    }
    System.out.println("before nations list length:" + jedis.llen(key));
    for (int i = 0; i < len; i++) {
      // System.out.print(jedis.lpop(key) + " ");
      System.out.print(jedis.rpop(key) + " ");
    }
    System.out.println("after  nations list length:" + jedis.llen(key));
  }

  public static void Redis_Lsit2() {
    String key = "nations";
    jedis.del(key);
    jedis.rpush(key, "中国");
    jedis.rpush(key, "俄国");
    jedis.rpush(key, "美国");
    jedis.rpush(key, "日国");
    jedis.rpush(key, "韩国");
    long len = jedis.llen(key);

    System.out.println("nations list length:" + len);
    List<String> list = jedis.lrange(key, 0, len - 1);
    for (String nation : list) {
      System.out.print(nation + " ");
    }
    System.out.println("before nations list length:" + jedis.llen(key));
    for (int i = 0; i < len; i++) {
      System.out.print(jedis.lpop(key) + " ");
      // System.out.print(jedis.rpop(key) + " ");
    }
    System.out.println("after  nations list length:" + jedis.llen(key));
  }

  public static void Redis_Lsit3() {
    String srcKey = "srcnations";
    String dstkey = "dstnations";
    jedis.del(srcKey);
    jedis.del(dstkey);
    jedis.rpush(srcKey, "中国");
    jedis.rpush(srcKey, "俄国");
    jedis.rpush(srcKey, "美国");
    jedis.rpush(srcKey, "日国");
    jedis.rpush(srcKey, "韓國");
    List<String> srcList = jedis.lrange(srcKey, 0, -1);
    for (String nation : srcList) {
      System.out.print(nation + " ");
    }
    System.out.println();

    System.out.println("srclen:" + jedis.llen(srcKey) + ",dstken:" + jedis.llen(dstkey));

    long srcLen = jedis.llen(srcKey);
    for (long i = 0; i < srcLen; i++) {
      String rpoplpush = jedis.rpoplpush(srcKey, dstkey);
      System.out.println(
          "rpoplpush:" + rpoplpush + "  srclen:" + jedis.llen(srcKey) + ",dstken:" + jedis.llen(dstkey));
    }

    List<String> dstList = jedis.lrange(dstkey, 0, -1);
    for (String nation : dstList) {
      System.out.print(nation + " ");
    }
  }

  public static void Redis_List4() {
    // 在集合的指定位置插入指定元素
    // 在元素 9 的前面插入元素99
    jedis.linsert("list", ListPosition.BEFORE, String.valueOf(9), String.valueOf(99));
    for (int i = 0; i < 10; i++) {
      jedis.lpush("list", String.valueOf(i));
    }
    System.out.print("ok");
  }

}
