package com.example.xml.ch.ch02.SAXDemo.v3;

import static java.lang.System.out;

import java.util.HashMap;
import java.util.Map;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class LocalRecipeML implements EntityResolver {

  private Map<String, String> mappings = new HashMap<>();

  LocalRecipeML() {
    mappings.put("-//FormatData//DTD RecipeML 0.5//EN",
        "recipeml.dtd");
  }

  @Override
  public InputSource resolveEntity(String publicId,
      String systemId) {
    if (mappings.containsKey(publicId)) {
      out.println("obtaining cached recipeml.dtd");
      systemId = mappings.get(publicId);
      InputSource localSource =
          new InputSource(systemId);
      return localSource;
    }
    return null;
  }
}
