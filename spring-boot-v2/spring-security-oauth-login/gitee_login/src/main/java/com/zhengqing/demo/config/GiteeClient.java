package com.zhengqing.demo.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.security.oauth2.client.gitee")
public class GiteeClient {
  public String clientId;
  public String clientSecret;
  public String redirectUri;
}
