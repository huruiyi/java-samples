package vip.fairy.impl;

import vip.fairy.DubboRemoteService;
import org.springframework.beans.factory.annotation.Value;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "${dubbo.global.version}", timeout = 1000)
public class DemoServiceImpl implements DubboRemoteService {

  @Value("${dubbo.application.name}")
  private String serviceName;

  @Override
  public String call(String name) {
    return String.format("Hello! I am %s, Nice to meet you!!!", name);
  }
}
