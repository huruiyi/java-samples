package vip.fairy;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AppTokenV2 {

  private final String tokenEndpoint;
  private final String clientId;
  private final String clientSecret;

  public AppTokenV2(String serverUrl, String realm, String clientId, String clientSecret) {
    this.tokenEndpoint = serverUrl + "/realms/" + realm + "/protocol/openid-connect/token";
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  // 使用用户凭证获取 Access Token
  public String getAccessToken(String username, String password) throws IOException {
    // 构建请求参数
    Map<String, String> params = new HashMap<>();
    params.put("grant_type", "password");
    params.put("client_id", clientId);
    params.put("client_secret", clientSecret);
    params.put("username", username);
    params.put("password", password);

    // 发送 POST 请求
    HttpURLConnection connection = (HttpURLConnection) new URL(tokenEndpoint).openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    connection.setDoOutput(true);

    // 写入请求参数
    try (OutputStream os = connection.getOutputStream()) {
      String requestBody = buildQueryString(params);
      os.write(requestBody.getBytes(StandardCharsets.UTF_8));
    }

    // 读取响应
    int responseCode = connection.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) {
      try (BufferedReader br = new BufferedReader(
          new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
          response.append(responseLine.trim());
        }

        // 解析 JSON 响应
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> tokenResponse = mapper.readValue(response.toString(), Map.class);
        return (String) tokenResponse.get("access_token");
      }
    } else {
      try (BufferedReader br = new BufferedReader(
          new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8))) {
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
          response.append(responseLine.trim());
        }
        throw new IOException("HTTP Error " + responseCode + ": " + response);
      }
    }
  }

  // 构建 URL 查询字符串
  private String buildQueryString(Map<String, String> params) {
    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, String> entry : params.entrySet()) {
      if (!result.isEmpty()) {
        result.append("&");
      }
      result.append(entry.getKey())
          .append("=")
          .append(entry.getValue());
    }
    return result.toString();
  }

  public static void main(String[] args) {
    try {
      AppTokenV2 client = new AppTokenV2(
          KeycloakConstants.serverUrl, KeycloakConstants.realm, KeycloakConstants.clientId, KeycloakConstants.clientSecret
      );
      String accessToken = client.getAccessToken(KeycloakConstants.username, KeycloakConstants.password);
      System.out.println("Access Token: " + accessToken);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
