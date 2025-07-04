package com.example.uploadingfiles;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig   {

    public void addArgumentResolvers(List<StandardServletMultipartResolver> resolvers) {
        resolvers.add(new StandardServletMultipartResolver());
    }
}
