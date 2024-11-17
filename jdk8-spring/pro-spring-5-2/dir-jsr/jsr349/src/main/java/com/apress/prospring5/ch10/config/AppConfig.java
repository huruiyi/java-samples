package com.apress.prospring5.ch10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan(basePackages = "com.apress.prospring5.ch10")
public class AppConfig {

	@Bean LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}
}


