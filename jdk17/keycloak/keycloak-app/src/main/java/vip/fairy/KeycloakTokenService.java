package vip.fairy;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;

public class KeycloakTokenService {

  private final String serverUrl;
  private final String realm;
  private final String clientId;
  private final String clientSecret;

  public KeycloakTokenService(String serverUrl, String realm, String clientId, String clientSecret) {
    this.serverUrl = serverUrl;
    this.realm = realm;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  // 使用服务账户获取 Access Token
  public String getAccessToken() {
    try {
      // 构建 Keycloak 客户端
      Keycloak keycloak = KeycloakBuilder.builder()
          .serverUrl(serverUrl)
          .realm(realm)
          .clientId(clientId)
          .clientSecret(clientSecret)
          .grantType(OAuth2Constants.CLIENT_CREDENTIALS) // 服务账户授权类型
          .build();

      // 获取 Access Token
      AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
      return tokenResponse.getToken();
    } catch (Exception e) {
      throw new RuntimeException("Failed to get access token", e);
    }
  }
}
