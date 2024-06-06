package com.example.demoD;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextD.xml")
public class JdbcDemo2 {

  @Resource(name = "jdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  @Test
  public void demo() {
    int result = 0;
    try {
      result = jdbcTemplate.update("insert into account values(null,?,?)", "小明", 10000d);

    } catch (Exception e) {
      System.err.println(e);
    }
    if (result > 0) {
      System.out.println("添加成功!");
    } else {
      System.out.println("添加失败!");
    }
  }
}
