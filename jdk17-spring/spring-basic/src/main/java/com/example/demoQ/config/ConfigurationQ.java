package com.example.demoQ.config;

import com.example.demoQ.DatePrefixGenerator;
import com.example.demoQ.NumberPrefixGenerator;
import com.example.demoQ.SequenceGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigurationQ {

  @Bean
  public DatePrefixGenerator datePrefixGenerator() {
    DatePrefixGenerator dpg = new DatePrefixGenerator();
    dpg.setPattern("yyyyMMdd");
    return dpg;
  }

  @Bean
  public NumberPrefixGenerator numberPrefixGenerator() {
    NumberPrefixGenerator npg = new NumberPrefixGenerator();
    return npg;
  }

  @Bean
  public SequenceGenerator sequenceGenerator() {
    SequenceGenerator sequence = new SequenceGenerator();
    sequence.setInitial(100000);
    sequence.setSuffix("A");
    return sequence;
  }
}
