package com.example.config;

import com.example.init.DBInitializer;
import javax.sql.DataSource;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = {"com.example"},
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = DBInitializer.class
        )
    }
)
@Profile("test")
public class ServiceTestConfig {

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
    ds.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8");
    ds.setUsername("root");
    ds.setPassword("root");
    return ds;
  }

  @Bean(name = "databaseTester")
  public DataSourceDatabaseTester dataSourceDatabaseTester() {
    return new DataSourceDatabaseTester(dataSource());
  }

  @Bean(name = "xlsDataFileLoader")
  public XlsDataFileLoader xlsDataFileLoader() {
    return new XlsDataFileLoader();
  }
}
