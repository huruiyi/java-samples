package vip.fairy;

public class AppTokenV1 {

  public static void main(String[] args) {
    //服务账户（Client Credentials 模式）
    KeycloakTokenService tokenService = new KeycloakTokenService(
        KeycloakConstants.serverUrl, KeycloakConstants.realm, KeycloakConstants.clientId, KeycloakConstants.clientSecret
    );
    String accessToken = tokenService.getAccessToken();
    System.out.println("Access Token: " + accessToken);
  }
}
