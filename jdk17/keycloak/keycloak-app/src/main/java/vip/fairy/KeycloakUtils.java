package vip.fairy;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakUtils {

  // 创建 Keycloak 管理客户端，需要关闭客户端认证
  static Keycloak getKeycloakInstanceBuAdminAccount() {
    return KeycloakBuilder.builder().serverUrl(KeycloakConstants.serverUrl).realm(KeycloakConstants.realm).clientId(KeycloakConstants.clientId).username(KeycloakConstants.username).password(KeycloakConstants.password).build();
  }

  /**
   * 客户端需要 manage-users 或 view-users 小权限。
   * <p>
   * 建议创建专用客户端并分配最小权限。
   * <p>
   * 需要分配服务账户角色相关权限（大），default-roles-master,admin,
   */
  public static Keycloak getKeycloakInstanceByServiceAccount() {
    return KeycloakBuilder.builder().serverUrl(KeycloakConstants.serverUrl).realm(KeycloakConstants.realm).clientId(KeycloakConstants.clientId).clientSecret(KeycloakConstants.clientSecret).grantType(OAuth2Constants.CLIENT_CREDENTIALS).build();
  }

}
