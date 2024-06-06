package com.example.xml.ch.ch11.JacksonDemo.v6;

import static java.lang.System.out;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import java.util.Map;

public class JacksonDemo {

  public static void main(String[] args) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String ageJson = "65";
    out.println(mapper.readValue(ageJson, Integer.class));
    String planetsJson = "[\"Mercury\", \"Venus\", " +
        "\"Earth\", \"Mars\"]";
    List<?> list1 = mapper.readValue(planetsJson,
        List.class);
    out.println(list1);
//      list1.add("Jupiter");
    List<String> list2 =
        mapper.readValue(planetsJson,
            new
                TypeReference<List<String>>() {
                });
    out.println(list2);
    list2.add("Jupiter");
    out.println(list2);
    String gradesJson = "{\"John\": 86, \"Jane\": 92}";
    Map<?, ?> map = mapper.readValue(gradesJson,
        Map.class);
    out.println(map);
    mapper.writeValue(new File("grades.json"), map);
  }
}
