package vip.fairy.xml.ch.ch09.GsonDemo.v10;

import static java.lang.System.out;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;

public class GsonDemo {

  public static void main(String[] args) {
    SomeClass sc = new SomeClass();
    sc.field = 1;
    GsonBuilder gsonb = new GsonBuilder();
    gsonb.setVersion(0.9);
    Gson gson = gsonb.create();
    out.printf("%s%n%n", gson.toJson(sc));
    gsonb.setVersion(1.0);
    gson = gsonb.create();
    out.printf("%s%n%n", gson.toJson(sc));
    gsonb.setVersion(1.1);
    gson = gsonb.create();
    out.printf("%s%n%n", gson.toJson(sc));
    gsonb.setVersion(1.5);
    gson = gsonb.create();
    out.printf("%s%n%n", gson.toJson(sc));
    gsonb.setVersion(2.5);
    gson = gsonb.create();
    out.printf("%s%n", gson.toJson(sc));
  }

  @Since(1.0)
  @Until(2.5)
  static class SomeClass {

    @Since(1.1)
    @Until(1.5)
    int field;
  }
}
