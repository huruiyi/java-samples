package vip.fairy.demoQ.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.fairy.demoQ.DatePrefixGenerator;
import vip.fairy.demoQ.NumberPrefixGenerator;
import vip.fairy.demoQ.SequenceGenerator;


@Configuration
public class SequenceConfiguration {

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
