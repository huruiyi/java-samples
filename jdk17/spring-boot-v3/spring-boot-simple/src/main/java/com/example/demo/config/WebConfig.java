package com.example.demo.config;

import com.example.demo.properties.FairyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FairyProperties.class)
public class WebConfig {

}
