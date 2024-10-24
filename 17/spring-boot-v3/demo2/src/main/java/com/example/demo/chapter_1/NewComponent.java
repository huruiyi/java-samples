package com.example.demo.chapter_1;

import com.example.demo.chapter_1.profilesconfig.DataSourceConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewComponent {
  @Autowired
  DataSourceConfig dataSourceConfig;

  @PostConstruct
  public void init(){
    dataSourceConfig.setup();
  }
}
