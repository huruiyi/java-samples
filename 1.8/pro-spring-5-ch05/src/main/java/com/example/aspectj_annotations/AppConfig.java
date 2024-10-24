package com.example.aspectj_annotations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.example.aspectj_annotations"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

}
