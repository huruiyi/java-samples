package com.example.demoJ.config;

import com.example.demoJ.SequenceGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigurationJ {

  @Bean
  public SequenceGenerator sequenceGenerator() {
    SequenceGenerator seqgen = new SequenceGenerator();
    seqgen.setPrefix("30");
    seqgen.setSuffix("A");
    seqgen.setInitial(100000);
    return seqgen;
  }

}
