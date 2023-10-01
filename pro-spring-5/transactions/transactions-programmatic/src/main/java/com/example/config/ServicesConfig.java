package com.example.config;

import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;


@Configuration
@ComponentScan(basePackages = "com.example")
public class ServicesConfig {

  final  EntityManagerFactory entityManagerFactory;

  public ServicesConfig(EntityManagerFactory entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
  }

  @Bean
  public TransactionTemplate transactionTemplate() {
    TransactionTemplate tt = new TransactionTemplate();
    tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_NEVER);
    tt.setTimeout(30);
    tt.setTransactionManager(transactionManager());
    return tt;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
