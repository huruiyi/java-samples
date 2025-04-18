package com.apress.prospring4.ch2.SpringAnnotation.test;

import com.apress.prospring4.ch2.SpringAnnotation.annotation.RpcService;
import java.lang.reflect.Method;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
// ApplicationContextAware会为Component组件调用setApplicationContext方法； 测试Myserver3时注释
public class MyServer1 implements ApplicationContextAware {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("SpringAnnotation.xml");
    ctx.close();
  }

  public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RpcService.class);
    for (Object serviceBean : serviceBeanMap.values()) {
      try {
        // 获取自定义注解上的value
        String value = serviceBean.getClass().getAnnotation(RpcService.class).value();
        System.out.println("注解上的value: " + value);

        // 反射被注解类，并调用指定方法
        Method method = serviceBean.getClass().getMethod("hello", String.class);
        Object invoke = method.invoke(serviceBean, "bbb");
        System.out.println(invoke);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
