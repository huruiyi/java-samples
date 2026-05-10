package com.sinopharm.app.config;

import org.flowable.app.spring.SpringAppEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessEngineConfig implements EngineConfigurationConfigurer<SpringAppEngineConfiguration> {

  private DatasourceConfigurator datasourceConfigurator;

  @Autowired
  public void setDatasourceConfigurator(DatasourceConfigurator datasourceConfigurator) {
    this.datasourceConfigurator = datasourceConfigurator;
  }

  @Override
  public void configure(SpringAppEngineConfiguration engineConfiguration) {
    engineConfiguration.addConfigurator(datasourceConfigurator);
  }

}
