package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;


@Configuration
@EnableAsync
@ComponentScan(basePackages  = {"com.example"} )
public class AppConfig {

	@Bean TaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor();
	}
}
