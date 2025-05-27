package com.roy.cls;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RecordTest {

  public static void main(String[] ar) {
    Point p = new Point(10, 20);
    for (Method method : p.getClass().getMethods()) {
      System.out.println(method);
    }
    for (Field field : p.getClass().getDeclaredFields()) {
      System.out.println(field);
      //不允许通过反射修改属性值
      //field.setAccessible(true);
      //field.set(p, 30);
    }
    System.out.println(p.x() + "====" + p.y());
  }
}
