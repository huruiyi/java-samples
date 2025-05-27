package com.example.proxyfactorybean_introduction;

import com.example.model.Contact;
import com.example.model.introduction.IsModifiedAdvisor;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public Contact guitarist() {
    Contact guitarist = new Contact();
    guitarist.setName("John Mayer");
    return guitarist;
  }

  @Bean
  public Advisor advisor() {
    return new IsModifiedAdvisor();
  }

  @Bean
  public ProxyFactoryBean bean() {
    ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
    proxyFactoryBean.setTarget(guitarist());
    proxyFactoryBean.setProxyTargetClass(true);
    proxyFactoryBean.addAdvisor(advisor());
    return proxyFactoryBean;
  }
}
