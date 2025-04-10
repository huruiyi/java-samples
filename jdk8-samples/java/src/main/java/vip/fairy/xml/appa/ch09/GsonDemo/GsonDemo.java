package vip.fairy.xml.appa.ch09.GsonDemo;

import static java.lang.System.out;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import java.lang.reflect.Modifier;

public class GsonDemo {

  public static void main(String[] args) {
    SomeClass sc = new SomeClass();
    sc.id = 1;
    sc.password = "abc";
    sc.field1 = 2;
    sc.field2 = 3;
    sc.field3 = 4;
    sc.field4 = 5;
    sc.field5 = 6;
    sc.field6 = 7;
    GsonBuilder gsonb = new GsonBuilder();
    gsonb.excludeFieldsWithoutExposeAnnotation();
    gsonb.excludeFieldsWithModifiers(Modifier.TRANSIENT);
    Gson gson = gsonb.create();
    String json = gson.toJson(sc);
    out.println(json);
    SomeClass sc2 = gson.fromJson(json, SomeClass.class);
    out.printf("id = %d%n", sc2.id);
    out.printf("password = %s%n", sc2.password);
    out.printf("field1 = %d%n", sc2.field1);
    out.printf("field2 = %d%n", sc2.field2);
    out.printf("field3 = %d%n", sc2.field3);
    out.printf("field4 = %d%n", sc2.field4);
    out.printf("field5 = %d%n", sc2.field5);
    out.printf("field6 = %d%n", sc2.field6);
  }

  static class SomeClass {

    @Expose(serialize = true, deserialize = true)
    static int field5;
    static int field6;
    transient int id;
    @Expose(serialize = true, deserialize = true)
    transient String password;
    @Expose(serialize = false, deserialize = false)
    int field1;
    @Expose(serialize = false, deserialize = true)
    int field2;
    @Expose(serialize = true, deserialize = false)
    int field3;
    @Expose(serialize = true, deserialize = true)
    int field4;
  }
}
