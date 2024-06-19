package com.packtpub.angularspringbook.greeting.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.packtpub.angularspringbook.greeting.model.Employee;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.jackson.JsonObjectSerializer;

@JsonComponent
public class MyJsonComponentV2 {

  public static class Serializer extends JsonObjectSerializer<Employee> {

    @Override
    protected void serializeObject(Employee value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
      jsonGenerator.writeStringField("name", value.getName());
      jsonGenerator.writeNumberField("age", value.getAge());
    }

  }

  public static class Deserializer extends JsonObjectDeserializer<Employee> {

    @Override
    protected Employee deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
      String name = nullSafeValue(tree.get("name"), String.class);
      int age = nullSafeValue(tree.get("age"), Integer.class);
      return new Employee(name, age);
    }

  }

}
