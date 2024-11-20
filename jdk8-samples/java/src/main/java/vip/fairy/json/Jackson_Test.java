package vip.fairy.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

public class Jackson_Test {

  @Test
  void Demo0() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    File file = ResourceUtils.getFile("classpath:user.json");
    Map<String, Object> userData = mapper.readValue(file, Map.class);
    System.out.println(userData);

    userData = new HashMap<>();
    Map<String, String> nameStruct = new HashMap<>();
    nameStruct.put("first", "ba");
    nameStruct.put("last", "ka");
    userData.put("name", nameStruct);
    userData.put("gender", "MALE");
    userData.put("verified", Boolean.FALSE);
    userData.put("userImage", "Rm9vYmFyIQ==img");

    String jsonPath = System.getProperty("user.dir") + "\\src\\main\\resources\\user-modified.json";
    mapper.writeValue(new File(jsonPath), userData);
    System.out.println(userData);
  }

  @Test
  void Demo1() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();

    String message = "{ \"name\" : \"Thomas\", \"message\" : \"Hello World\" }";

    JsonNode jsonMessage = objectMapper.readTree(message);
    JsonNode name = jsonMessage.get("name");
    String s = name.textValue();
    System.out.println(s);
  }

  @Test
  void Demo2() throws IOException {
    ClassPathResource classPathResource = new ClassPathResource("user.json");
    InputStream inputStream0 = classPathResource.getInputStream();
    System.out.println(inputStream0.read());

    InputStream inputStream1 = Thread.currentThread().getContextClassLoader().getResourceAsStream("user.json");
    System.out.println(inputStream1.read());

    // InputStream inputStream2 = Jackson_Test.class.getResourceAsStream("user.json");
    // System.out.println(inputStream2.read());

    //不可使用，打包后无法访问
    File file = ResourceUtils.getFile("classpath:user.json");
    FileInputStream inputStream = new FileInputStream(file);
    System.out.println(inputStream.read());
  }
}
