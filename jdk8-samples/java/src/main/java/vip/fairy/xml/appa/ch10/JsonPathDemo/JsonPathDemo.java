package vip.fairy.xml.appa.ch10.JsonPathDemo;

import static java.lang.System.out;

import com.jayway.jsonpath.JsonPath;

public class JsonPathDemo {

  public static void main(String[] args) {
    String json = "{ \"numbers\": [10, 20, 25, 30] }";

    String expr = "$.numbers.max()";
    double d = JsonPath.read(json, expr);
    out.printf("Max value = %f%n", d);
    expr = "$.numbers.min()";
    d = JsonPath.read(json, expr);
    out.printf("Min value = %f%n", d);
    expr = "$.numbers.avg()";
    d = JsonPath.read(json, expr);
    out.printf("Average value = %f%n", d);
  }
}
