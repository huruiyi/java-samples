package com.example.xml.ch.ch11.JacksonDemo.v1;

import static java.lang.System.out;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.File;

public class JacksonDemo {

  public static void main(String[] args) throws Exception {
    JsonFactory factory = new JsonFactory();
    JsonParser parser = factory.createParser(new File("../com/example/Xml_ch/ch11/JacksonDemo/v1/person.json"));
    while (!parser.isClosed()) {
      JsonToken jsonToken = parser.nextToken();
      if (jsonToken == null) {
        break;
      }
      out.printf("jsonToken = %s [%s] [%b] [%s]%n",
          jsonToken, jsonToken.asString(),
          jsonToken.isNumeric(),
          parser.getValueAsString());
    }
  }
}
