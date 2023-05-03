package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class SpringJdbcAnnotationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcAnnotationsApplication.class, args);
    }

}
