package vip.fairy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "example.swagger")
public class SwaggerProperties {

  private String title = "在线文档"; //标题
  private String group = ""; //自定义组名
  private String description = "在线文档"; //描述
  private String version = "1.0"; //版本
  private Contact contact = new Contact(); //联系人


  private List<String> basePath = new ArrayList<>(); //swagger会解析的url规则
  private List<String> excludePath = new ArrayList<>();//在basePath基础上需要排除的url规则
  private Map<String, DocketInfo> docket = new LinkedHashMap<>(); //分组文档

  public String getGroup() {
    if (group == null || group.isEmpty()) {
      return title;
    }
    return group;
  }

  @Data
  public static class DocketInfo {

    private String title = "在线文档"; //标题
    private String group = ""; //自定义组名
    private String description = "在线文档"; //描述
    private String version = "1.0"; //版本
    private Contact contact = new Contact(); //联系人
    private String basePackage = ""; //swagger会解析的包路径
    private List<String> basePath = new ArrayList<>(); //swagger会解析的url规则
    private List<String> excludePath = new ArrayList<>();//在basePath基础上需要排除的url

    public String getGroup() {
      if (group == null || group.isEmpty()) {
        return title;
      }
      return group;
    }
  }

  @Data
  public static class Contact {
    private String name = "pinda"; //联系人
    private String url = ""; //联系人url
    private String email = ""; //联系人email
  }

}
