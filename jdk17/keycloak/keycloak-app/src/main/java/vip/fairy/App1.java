package vip.fairy;


import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;


public class App1 {

  private static final String clientId = "admin-cli";
  private static final String realmName = "master";
  private static final String userName = "admin";
  private static final String password = "fairy-vip";
  private static final String authServerUrl = "http://localhost:8080";


  // 登录认证
  @Test
  public void authUser() {
    // 根据keycloak.json中定义的配置创建一个新实例
    AuthzClient authzClient = AuthzClient.create();
    try {
      //admin-cli 需要关闭客户端认证
      AccessTokenResponse response = authzClient.obtainAccessToken(userName, password);
      String token = response.getToken();
      System.out.println("ok：" + token);
    } catch (Exception e) {
      System.out.println("not ok");
    }
  }

  // 创建realm
  @Test
  public void createRealm() {
    String newRealm = "mes";
    RealmRepresentation realm = new RealmRepresentation();
    realm.setRealm(newRealm);
    realm.setEnabled(true);
    Keycloak().realms().create(realm);
  }

  // 创建client
  @Test
  public void createClient() {
    ClientRepresentation client = new ClientRepresentation();
    client.setClientId("new-client");
    Response response = Keycloak().realm(realmName).clients().create(client);
    System.out.println(response.getStatus());
  }

  // 创建user
  @Test
  public void createUser() {
    UserRepresentation user = new UserRepresentation();
    user.setUsername("user-3");
    CredentialRepresentation credential = new CredentialRepresentation();
    credential.setType(CredentialRepresentation.PASSWORD);
    credential.setValue("user-3-pass");
    credential.setTemporary(false);
    List<CredentialRepresentation> credentials = new ArrayList<>();
    credentials.add(credential);
    user.setCredentials(credentials);
    user.setEnabled(true);
    Response response = Keycloak().realm(realmName).users().create(user);
    System.out.println(response.getStatus());
  }

  /**
   * 管理员用户名密码认证
   *
   * @return Keycloak实例
   */
  private static Keycloak Keycloak() {
    return KeycloakBuilder.builder()
        .serverUrl(authServerUrl)
        .realm(realmName)
        .username(userName)
        .password(password)
        .clientId(clientId).build();
  }

}
