package com.apress.prospring4.ch2.customScopeAnnotation;

import com.apress.prospring4.ch2.customScope.ThreadScope;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class AppConfig {

  @Bean
  public static CustomScopeConfigurer customScopeConfigurer() {
    CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("threadScope", new ThreadScope());

    // 配置scope
    customScopeConfigurer.setScopes(map);
    return customScopeConfigurer;
  }
}
