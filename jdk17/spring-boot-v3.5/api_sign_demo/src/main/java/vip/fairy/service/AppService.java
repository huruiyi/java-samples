package vip.fairy.service;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class AppService {

  private static final Map<String, String> APP_INFO = Map.of("app1", "sign1", "app2", "sign2");

  public String getAppKey(String appId) {
    return APP_INFO.get(appId);
  }

}
