package com.example.spring;

import com.example.service.HelloPrint;
import com.example.service.HelloPrintImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.example")
public class AppConfig {

  /**
   * 如果实现类HelloPrintImpl上定义了@Component ，此处就不需要
   */
  @Bean
  public HelloPrint helloPrint() {
    return new HelloPrintImpl();
  }

}
