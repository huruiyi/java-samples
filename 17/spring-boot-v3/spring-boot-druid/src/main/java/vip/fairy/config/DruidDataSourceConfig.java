package vip.fairy.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.jakarta.StatViewServlet;
import com.alibaba.druid.support.jakarta.WebStatFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidDataSourceConfig {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
  public DataSource druid() {
    return new DruidDataSource();
  }

  @Bean
  @ConditionalOnClass(DruidDataSource.class)
  public ServletRegistrationBean<StatViewServlet> statViewServlet() {
    // 这些参数可以在 http.StatViewServlet 的父类 ResourceServlet 中找到
    Map<String, String> initParams = new HashMap<>();
    initParams.put("loginUsername", "admin");
    initParams.put("loginPassword", "123456");
    // allow：Druid 后台允许谁可以访问。默认就是允许所有访问。
    initParams.put("allow", ""); // 后面参数为空则所有人都能访问，一般会写一个具体的ip或ip段
    // deny：Druid 后台禁止谁能访问
    // initParams.put("deny","192.168.10.132");
    // 注册一个servlet，同时表明/druid/* 这个请求会走到这个servlet，而druid内置了这个请求的接收
    ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
    bean.setInitParameters(initParams);
    return bean;
  }

  @Bean
  @ConditionalOnClass(DruidDataSource.class)
  public FilterRegistrationBean<WebStatFilter> webStatFilter() {
    Map<String, String> initParams = new HashMap<>();
    // 这些不进行统计
    initParams.put("exclusions", "*.js,*.css,/druid/*");

    FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
    bean.setFilter(new WebStatFilter());
    bean.setInitParameters(initParams);
    bean.setUrlPatterns(List.of("/*"));
    return bean;
  }

}
