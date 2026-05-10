package com.sinopharm.app.config;

import org.flowable.common.engine.impl.AbstractEngineConfiguration;
import org.flowable.common.engine.impl.EngineConfigurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatasourceConfigurator implements EngineConfigurator {

  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.driver-class-name}")
  private String driverClassName;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Override
  public void beforeInit(AbstractEngineConfiguration engineConfiguration) {
    DataSource dataSource = DataSourceBuilder.create()
        .driverClassName(driverClassName)
        .url(url)
        .username(username)
        .password(password).build();
    engineConfiguration.setDataSource(dataSource);
  }

  @Override
  public void configure(AbstractEngineConfiguration engineConfiguration) {
  }

  @Override
  public int getPriority() {
    return 600000; // 保证该优先级最高
  }

}
