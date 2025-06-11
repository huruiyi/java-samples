package vip.fairy.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import vip.fairy.service.AppService;
import vip.fairy.utils.SignUtils;

public class SignInterceptor implements HandlerInterceptor {

  public final static Logger logger = LoggerFactory.getLogger(SignInterceptor.class);
  AppService appService;

  public SignInterceptor(AppService appService) {
    this.appService = appService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    String appId = request.getHeader("appId");
    String timestamp = request.getHeader("timestamp");
    String sign = request.getHeader("sign");
    if (StringUtils.hasText(appId) && StringUtils.hasText(timestamp) && StringUtils.hasText(sign)) {
      Instant instant = Instant.ofEpochMilli(Long.parseLong(timestamp));
      LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
      if (LocalDateTime.now().isBefore(localDateTime)) {
        String originalSign = appId + "-" + appService.getAppKey(appId) + "-" + timestamp;
        if (SignUtils.verify(appService.getAppKey(appId), originalSign, sign)) {
          return true;
        } else {
          logger.error("签名验证失败");
        }
      } else {
        logger.error("签名已过期");
      }
    } else {
      logger.error("签名信息不完整");
    }
    response.setStatus(401);
    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
