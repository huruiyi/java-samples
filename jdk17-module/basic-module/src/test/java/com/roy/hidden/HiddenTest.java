package com.roy.hidden;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import org.junit.Test;

public class HiddenTest {

  @Test
  public void printHiddenClassBytesInBase64() {
    String classPath = "C:\\Users\\hurui\\Downloads\\JDK17Demo\\basicModule\\target\\classes\\com\\roy\\hidden\\HiddenClass.class";
    try {
      byte[] bytes = Files.readAllBytes(Paths.get(classPath));
      System.out.println(Base64.getEncoder().encodeToString(bytes));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testInvokeHiddenClass() throws Throwable {
    //class文件的字节码
    String CLASS_INFO = "yv66vgAAAD0APAoAAgADBwAEDAAFAAYBABBqYXZhL2xhbmcvT2JqZWN0AQAGPGluaXQ+AQADKClWEgAAAAgMAAkACgEAF21ha2VDb25jYXRXaXRoQ29uc3RhbnRzAQAmKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1N0cmluZzsJAAwADQcADgwADwAQAQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOwgAEgEAI0hlbGxvLCAlcyAhCkhlbGxvLCBIaWRkZW5DbGFzcyAhCiVuCgAUABUHABYMABcAGAEAE2phdmEvaW8vUHJpbnRTdHJlYW0BAAZwcmludGYBADwoTGphdmEvbGFuZy9TdHJpbmc7W0xqYXZhL2xhbmcvT2JqZWN0OylMamF2YS9pby9QcmludFN0cmVhbTsIABoBAAtIaWRkZW5DbGFzcwoAFAAcDAAdAB4BAAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWBwAgAQAaY29tL3JveS9oaWRkZW4vSGlkZGVuQ2xhc3MBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEAHExjb20vcm95L2hpZGRlbi9IaWRkZW5DbGFzczsBAAhzYXlIZWxsbwEABG5hbWUBABJMamF2YS9sYW5nL1N0cmluZzsBAApwcmludEhlbGxvAQAIPGNsaW5pdD4BAApTb3VyY2VGaWxlAQAQSGlkZGVuQ2xhc3MuamF2YQEAEEJvb3RzdHJhcE1ldGhvZHMPBgAvCgAwADEHADIMAAkAMwEAJGphdmEvbGFuZy9pbnZva2UvU3RyaW5nQ29uY2F0RmFjdG9yeQEAmChMamF2YS9sYW5nL2ludm9rZS9NZXRob2RIYW5kbGVzJExvb2t1cDtMamF2YS9sYW5nL1N0cmluZztMamF2YS9sYW5nL2ludm9rZS9NZXRob2RUeXBlO0xqYXZhL2xhbmcvU3RyaW5nO1tMamF2YS9sYW5nL09iamVjdDspTGphdmEvbGFuZy9pbnZva2UvQ2FsbFNpdGU7CAA1AQAISGVsbG8sIAEBAAxJbm5lckNsYXNzZXMHADgBACVqYXZhL2xhbmcvaW52b2tlL01ldGhvZEhhbmRsZXMkTG9va3VwBwA6AQAeamF2YS9sYW5nL2ludm9rZS9NZXRob2RIYW5kbGVzAQAGTG9va3VwACEAHwACAAAAAAAEAAEABQAGAAEAIQAAAC8AAQABAAAABSq3AAGxAAAAAgAiAAAABgABAAAAAwAjAAAADAABAAAABQAkACUAAAABACYACgABACEAAAA7AAEAAgAAAAcrugAHAACwAAAAAgAiAAAABgABAAAACAAjAAAAFgACAAAABwAkACUAAAAAAAcAJwAoAAEACQApAB4AAQAhAAAAQAAGAAEAAAASsgALEhEEvQACWQMqU7YAE1exAAAAAgAiAAAACgACAAAADAARABAAIwAAAAwAAQAAABIAJwAoAAAACAAqAAYAAQAhAAAAJQACAAAAAAAJsgALEhm2ABuxAAAAAQAiAAAACgACAAAABQAIAAYAAwArAAAAAgAsAC0AAAAIAAEALgABADQANgAAAAoAAQA3ADkAOwAZ";
    //JDK17新增
    byte[] classInBytes = Base64.getDecoder().decode(CLASS_INFO);
    Class<?> proxy = MethodHandles.lookup().defineHiddenClass(classInBytes, true, MethodHandles.Lookup.ClassOption.NESTMATE).lookupClass();

    // 输出类名
    System.out.println(proxy.getName());
    // 输出类有哪些函数
    for (Method method : proxy.getDeclaredMethods()) {
      System.out.println(method.getName());
    }
    // 2. 调用对应的方法-JDK8已经支持
    MethodHandle mhPrintHello = MethodHandles.lookup().findStatic(proxy, "printHello", MethodType.methodType(void.class, String.class));
    mhPrintHello.invokeExact("loulan");
    Object proxyObj = proxy.getConstructors()[0].newInstance();
    MethodHandle mhSayHello = MethodHandles.lookup().findVirtual(proxy, "sayHello", MethodType.methodType(String.class, String.class));
    System.out.println(mhSayHello.invoke(proxyObj, "loulan"));
    // 用反射
    Method sayHello = proxy.getDeclaredMethod("sayHello", String.class);
    System.out.println(sayHello.invoke(proxyObj, "loulan"));
    Method printHello = proxy.getDeclaredMethod("printHello", String.class);
    printHello.invoke(null, "loulan");
  }
}
