package com.example.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Demo1 {

  public static void main(String[] args) {
    Apple apple = new Apple();
    System.out.println(apple);

    List<Field> fields = Arrays.asList(apple.getClass().getDeclaredFields());
    for (Field field : fields) {
      Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
      for (Annotation annotation : declaredAnnotations) {
        if (FruitName.class == annotation.annotationType()) {
          FruitName fruitName = (FruitName) annotation;
          System.out.println(fruitName.value());
        }
        if (FruitColor.class == annotation.annotationType()) {
          FruitColor fruitColor = (FruitColor) annotation;
          System.out.println(fruitColor.fruitColor());
        }
        System.out.println(annotation);
      }
    }
  }
}
