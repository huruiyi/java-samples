package vip.fairy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-redis.xml")
public class TestRedisTemplate {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Test
  public void test01() {
    ValueOperations<String, String> operations = redisTemplate.opsForValue();
    String key = "name";
    operations.set(key, "zhangsan");
    Object name = operations.get(key);
    System.out.println("name:" + name);
    operations.append(key, "is man");
    operations.getAndSet(key, "zhangsan-1");
    Object name1 = operations.get(key);
    System.out.println("修改后：" + name1);
  }

}
