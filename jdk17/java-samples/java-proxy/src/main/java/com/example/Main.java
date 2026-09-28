package com.example;

import com.example.bytebuddy.LoggingInterceptor;
import com.example.cglib.CglibMethodInterceptor;
import com.example.jdk.HelloPrintStaticProxy;
import com.example.jdk.LoggingInvocationHandler;
import com.example.service.HelloPrint;
import com.example.service.HelloPrintImpl;
import com.example.spring.AppConfig;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {


  public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    test8();
  }

  public static void test8() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
      // 创建 HelloPrintImpl 的子类，并拦截所有方法（排除 Object 类的方法）
    Class<? extends HelloPrintImpl> proxyClass = new ByteBuddy()
        .subclass(HelloPrintImpl.class)
        .method(ElementMatchers.any().and(ElementMatchers.not(ElementMatchers.isDeclaredBy(Object.class))))
        .intercept(MethodDelegation.to(LoggingInterceptor.class))
        .make()
        .load(Main.class.getClassLoader())
        .getLoaded();

    HelloPrintImpl proxy = proxyClass.getDeclaredConstructor().newInstance();
    proxy.printStr("Alice", "Bob");
  }


  static void test7() {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    HelloPrint printer = context.getBean(HelloPrint.class);
    printer.printStr("Alice", "Bob");
  }

  static void test6() {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    HelloPrint printer = applicationContext.getBean(HelloPrint.class);
    printer.printStr("Alice", "Bob");
  }


  /**
   * CGLIB 代理 --add-opens java.base/java.lang=ALL-UNNAMED
   */
  static void test5() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(HelloPrintImpl.class);
    enhancer.setCallback(new CglibMethodInterceptor());

    HelloPrintImpl proxy = (HelloPrintImpl) enhancer.create();
    proxy.printStr("CGLIB", "Proxy");
  }

  /**
   * 静态代理（手写代理类）
   */
  static void test4() {
    HelloPrint proxy = new HelloPrintStaticProxy(new HelloPrintImpl());
    proxy.printStr("Tom", "Jerry");
  }

  /**
   * 动态代理（JDK）
   */
  static void test3() {
    HelloPrint target = new HelloPrintImpl();

    // 创建代理实例
    HelloPrint proxy = (HelloPrint) Proxy.newProxyInstance(
        target.getClass().getClassLoader(),
        target.getClass().getInterfaces(), // 必须是接口数组
        new LoggingInvocationHandler(target)
    );

    proxy.printStr("Alice", "Bob");
  }

  /**
   * 动态代理（JDK）
   */
  static void test2() {
    HelloPrintImpl helloPrint1 = new HelloPrintImpl();
    HelloPrint printer = (HelloPrint) Proxy.newProxyInstance(
        helloPrint1.getClass().getClassLoader(),
        helloPrint1.getClass().getInterfaces(),
        (proxy, method, args1) -> method.invoke(helloPrint1, args1));
    printer.printStr("Alice", "Bob");
  }

  /**
   * 动态代理（JDK）
   */
  static void test1() {
    HelloPrintImpl helloPrint1 = new HelloPrintImpl();
    HelloPrint printer = (HelloPrint) Proxy.newProxyInstance(
        helloPrint1.getClass().getClassLoader(),
        helloPrint1.getClass().getInterfaces(),
        new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(helloPrint1, args);
          }
        });
    printer.printStr("Alice", "Bob");
  }


}


