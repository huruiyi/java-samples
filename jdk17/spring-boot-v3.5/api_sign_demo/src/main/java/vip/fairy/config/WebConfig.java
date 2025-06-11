package vip.fairy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vip.fairy.interceptor.SignInterceptor;
import vip.fairy.service.AppService;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  AppService appService;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new SignInterceptor(appService))
        //只拦截需要接口验签的请求
        .addPathPatterns("/user/**");
  }
}
