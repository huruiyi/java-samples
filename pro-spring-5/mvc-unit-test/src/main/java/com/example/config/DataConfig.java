package com.example.config;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by iuliana.cosmina on 6/25/17.
 */
@Profile("dev")
@Configuration
@ComponentScan(basePackages = {"com.example.init"})
public class DataConfig {

  private static Logger logger = LoggerFactory.getLogger(DataConfig.class);

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
    ds.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8");
    ds.setUsername("root");
    ds.setPassword("root");
    return ds;
  }

}
