package com.example.bytebuddy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

public class LoggingInterceptor {

  public static void intercept(@Origin Method method, @AllArguments Object[] args, @SuperCall Callable<Void> callable) throws Exception {
    System.out.println("[BYTEBUDDY] 调用方法: " + method.getName() + ", 参数: " + java.util.Arrays.toString(args));
    callable.call();
    System.out.println("[BYTEBUDDY] 方法执行完毕");
  }

}
