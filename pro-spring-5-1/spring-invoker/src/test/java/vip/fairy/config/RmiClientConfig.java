package vip.fairy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import vip.fairy.services.SingerService;

@Configuration
public class RmiClientConfig {

    @Bean
    public SingerService singerService() {
        HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceInterface(SingerService.class);
        factoryBean.setServiceUrl("http://localhost:8080/invoker/httpInvoker/singerService");
        factoryBean.afterPropertiesSet();
        return (SingerService) factoryBean.getObject();
    }
}
