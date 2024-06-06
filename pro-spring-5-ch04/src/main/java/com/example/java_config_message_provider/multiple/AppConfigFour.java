package com.example.java_config_message_provider.multiple;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.example.java_config_message_provider.annotated"})
public class AppConfigFour {

}
