package vip.fairy;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.List;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;

public class AppUser {


  public static void main(String[] args) {
    //客户端需要 manage-users 或 view-users 权限。建议创建专用客户端并分配最小权限。
    Keycloak keycloak = KeycloakUtils.getKeycloakInstanceByServiceAccount();
    //true表示精确查询
    List<UserRepresentation> list =
        keycloak.realm(KeycloakConstants.realm).users().searchByUsername(KeycloakConstants.username, true);

    // 获取指定 realm 中的用户资源
    UserRepresentation userRepresentation =
        keycloak.realm(KeycloakConstants.realm).users().get(list.get(0).getId()).toRepresentation();
    System.out.println(list.get(0).getUsername().equals(userRepresentation.getUsername()));

    System.out.println("ID: " + userRepresentation.getId());
    System.out.println("Username: " + userRepresentation.getUsername());
    System.out.println("Email: " + userRepresentation.getEmail());
    System.out.println("First Name: " + userRepresentation.getFirstName());
    System.out.println("Last Name: " + userRepresentation.getLastName());
    System.out.println("Enabled: " + userRepresentation.isEnabled());
    System.out.println("Email Verified: " + userRepresentation.isEmailVerified());
    System.out.println("Attributes: " + userRepresentation.getAttributes()); // 自定义属性
    System.out.println("Roles: " + userRepresentation.getRealmRoles());      // 领域角色

    String token = keycloak.tokenManager().getAccessToken().getToken();
    demo1(token, userRepresentation.getId());
  }

  public static void demo1(String accessToken, String userId) {
    //https://www.keycloak.org/docs-api/latest/rest-api/index.html#_users
    HttpClient client = HttpClient.newBuilder().build();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(MessageFormat.format("{0}/admin/realms/{1}/users/{2}",
            KeycloakConstants.serverUrl, KeycloakConstants.realm, userId)))
        .header("Authorization", "Bearer " + accessToken)
        .build();

    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(response.body());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
