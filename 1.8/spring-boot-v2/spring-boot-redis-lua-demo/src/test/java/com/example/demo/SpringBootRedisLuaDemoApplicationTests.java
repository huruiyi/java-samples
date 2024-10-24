package com.example.demo;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

@SpringBootTest
class SpringBootRedisLuaDemoApplicationTests {


  @Autowired
  private StringRedisTemplate redisTemplate;

  private static final String RELEASE_LOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";


  @Test
  void contextLoads1() {
    String lockKey = "123";
    String UUID = cn.hutool.core.lang.UUID.fastUUID().toString();
    boolean success = Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(lockKey, UUID, 3, TimeUnit.MINUTES));
    if (!success) {
      System.out.println("锁已存在");
    }
    // 执行 lua 脚本
    DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
    // 指定 lua 脚本
    redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/DelKey.lua")));
    // 指定返回类型
    redisScript.setResultType(Long.class);
    // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
    Long result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), UUID);
    System.out.println(result);

  }

  @Test
  void contextLoads2() {
    String lockKey = "123";
    String UUID = cn.hutool.core.lang.UUID.fastUUID().toString();
    boolean success = Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(lockKey, UUID, 3, TimeUnit.MINUTES));
    if (!success) {
      System.out.println("锁已存在");
    }
    // 指定 lua 脚本，并且指定返回值类型
    DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(RELEASE_LOCK_LUA_SCRIPT, Long.class);
    // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
    Long result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), UUID);
    System.out.println(result);
  }

}
