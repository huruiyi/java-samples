package com.example.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration implements BeanFactoryAware {

  private BeanFactory beanFactory;

  @Bean(value = "defaultApi2")
  public Docket defaultApi2() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfoBuilder()
            .title("swagger-bootstrap-ui-demo RESTful APIs")
            .description("# swagger-bootstrap-ui-demo RESTful APIs")
            .termsOfServiceUrl("http://www.xx.com/").contact("xx@qq.com")
            .version("1.0").build())
        //分组名称
        .groupName("2.X版本").select()
        //这里指定Controller扫描包路径
        .apis(RequestHandlerSelectors.basePackage("com.example.api")).paths(PathSelectors.any()).build();
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }
}