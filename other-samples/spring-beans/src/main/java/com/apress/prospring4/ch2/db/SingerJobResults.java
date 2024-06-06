package com.apress.prospring4.ch2.db;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class SingerJobResults {

  public static void main(String... args) {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(SingerConfig.class);
    JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
    List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM user");
    for (Map<String, Object> result : results) {
      System.out.println(result.toString());
    }
    ctx.close();
  }

  @Configuration
  static class SingerConfig {

    @Bean
    JdbcTemplate jdbcTemplate() {
      return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
      dataSource.setUsername("root");
      dataSource.setPassword("root");
      return dataSource;
    }
  }
}
