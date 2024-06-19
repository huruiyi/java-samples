package com.packtpub.angularspringbook.greeting.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.packtpub.angularspringbook.greeting.model.Person;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class MyJsonComponent {

  public static class Serializer extends JsonSerializer<Person> {

    @Override
    public void serialize(Person value, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
      jgen.writeStartObject();
      jgen.writeStringField("1:name", value.getName());
      jgen.writeEndObject();
    }

  }

  public static class Deserializer extends JsonDeserializer<Person> {

    @Override
    public Person deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
      ObjectCodec codec = jsonParser.getCodec();
      JsonNode tree = codec.readTree(jsonParser);
      String name = tree.get("name").textValue();
      return new Person(name);
    }

  }

}
