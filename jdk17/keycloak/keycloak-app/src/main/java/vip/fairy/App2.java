package vip.fairy;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

public class App2 {


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



  public static UserRepresentation getUserByName(String userName) {
    List<UserRepresentation> list = KeycloakUtils.getKeycloakInstanceByServiceAccount().realm(KeycloakConstants.realm).users().searchByUsername(userName, true);
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
    try (Keycloak keycloak = KeycloakUtils.getKeycloakInstanceByServiceAccount()) {
      return keycloak.realm(KeycloakConstants.realm)
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
    try (Keycloak keycloak = KeycloakUtils.getKeycloakInstanceByServiceAccount()) {
      return keycloak.realm(KeycloakConstants.realm)
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
