package vip.fairy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.fairy.model.Boy;
import vip.fairy.model.Girl;

@Configuration(proxyBeanMethods = true)
public class BeanConfig {

  @Bean
  public Boy getBoy() {
    return new Boy("Jerry", 18);
  }

  @Bean
  public Girl getGirl() {
    Girl girl = new Girl();
    girl.setName("Susan");
    girl.setAge(18);
    girl.setBoyFriend(getBoy());
    return girl;
  }


}
