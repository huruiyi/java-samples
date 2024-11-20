package vip.fairy.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;


public class Apple {

  @FruitName("Apple")
  private String appleName;

  @FruitColor(fruitColor = FruitColor.Color.GREEN)
  private String appleColor;

  public String getAppleColor() {
    return appleColor;
  }

  public void setAppleColor(String appleColor) {
    this.appleColor = appleColor;
  }

  public String getAppleName() {
    return appleName;
  }

  public void setAppleName(String appleName) {
    this.appleName = appleName;
  }

  public void displayName() {
    System.out.println("水果的名字是：苹果");
  }

  @Override
  public String toString() {
    String appleName = "";
    FruitColor.Color appleColor = null;
    List<Field> fields = Arrays.asList(this.getClass().getDeclaredFields());
    for (Field field : fields) {
      Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
      for (Annotation annotation : declaredAnnotations) {

        if (FruitName.class == annotation.annotationType()) {
          FruitName fruitName = (FruitName) annotation;
          appleName = fruitName.value();
        }

        if (FruitColor.class == annotation.annotationType()) {
          FruitColor fruitColor = (FruitColor) annotation;
          appleColor = fruitColor.fruitColor();
        }
      }
    }
    return "Apple [appleName=" + appleName + ", appleColor=" + appleColor + "]";
  }

}
