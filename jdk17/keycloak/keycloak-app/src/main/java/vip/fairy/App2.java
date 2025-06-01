package vip.fairy;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

public class App2 {

  public final static String serverUrl = "http://localhost:8080";
  public final static String realm = "master";
  public final static String clientId = "admin-cli";
  public final static String clientSecret = "vcGsznZbblHCJU2UuxFJabAV0haS8jOV";
  public final static String username = "admin";
  public final static String password = "fairy-vip";

  public static void main(String[] args) {
    UserRepresentation user0 = getUserByName("huruiyi");
    System.out.println("user0 found: " + user0.getUsername());
    // 查询用户
    UserRepresentation user1 = getUserById("3c0bd4f0-44cf-4ff8-8fd5-29d547d92a78");
    if (user1 != null) {
      System.out.println("user1 found: " + user1.getUsername());

      // 获取用户角色
      List<String> roles = getUserRoles("3c0bd4f0-44cf-4ff8-8fd5-29d547d92a78");
      System.out.println("roles: " + roles);
    }
  }

  // 创建 Keycloak 管理客户端，需要关闭客户端认证
  private static Keycloak getKeycloakInstanceV1() {
    return KeycloakBuilder.builder()
        .serverUrl(serverUrl)
        .realm(realm)
        .clientId(clientId)
        .username(username)
        .password(password)
        .build();
  }

  /**
   * 需要分配服务账户角色相关权限，default-roles-master,admin
   */
  public static Keycloak getKeycloakInstanceV2() {
    return KeycloakBuilder.builder()
        .serverUrl(serverUrl)
        .realm(realm)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
        .build();
  }

  public static UserRepresentation getUserByName(String userName) {
    List<UserRepresentation> list = getKeycloakInstanceV2().realm(realm).users().searchByUsername(userName, true);
    UserRepresentation user = list.get(0);

    System.out.println("ID: " + user.getId());
    System.out.println("Username: " + user.getUsername());
    System.out.println("Email: " + user.getEmail());
    System.out.println("First Name: " + user.getFirstName());
    System.out.println("Last Name: " + user.getLastName());
    System.out.println("Enabled: " + user.isEnabled());
    System.out.println("Email Verified: " + user.isEmailVerified());
    System.out.println("Attributes: " + user.getAttributes()); // 自定义属性
    System.out.println("Roles: " + user.getRealmRoles());      // 领域角色
    return user;
  }

  public static UserRepresentation getUserById(String userId) {
    try (Keycloak keycloak = getKeycloakInstanceV2()) {
      return keycloak.realm(realm)
          .users()
          .get(userId)
          .toRepresentation();
    } catch (NotFoundException e) {
      System.err.println("User not found with ID: " + userId);
      return null;
    } catch (Exception e) {
      System.err.println("Error fetching user: " + e.getMessage());
      return null;
    }
  }

  // 获取用户的所有角色
  public static List<String> getUserRoles(String userId) {
    try (Keycloak keycloak = getKeycloakInstanceV2()) {
      return keycloak.realm(realm)
          .users()
          .get(userId)
          .roles()
          .realmLevel() //领域角色
          .listAll()
          .stream()
          .map(RoleRepresentation::getName)
          .toList();
    } catch (Exception e) {
      System.err.println("Error fetching user roles: " + e.getMessage());
      return List.of();
    }
  }

}
