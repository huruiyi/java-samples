package vip.fairy;


import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;


public class App1 {

  // 登录认证
  @Test
  public void authUser() {
    // 根据keycloak.json中定义的配置创建一个新实例
    AuthzClient authzClient = AuthzClient.create();
    try {
      //admin-cli 需要关闭客户端认证
      AccessTokenResponse response = authzClient.obtainAccessToken(KeycloakConstants.username, KeycloakConstants.password);
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
    KeycloakUtils.getKeycloakInstanceBuAdminAccount().realms().create(realm);
  }

  // 创建client
  @Test
  public void createClient() {
    ClientRepresentation client = new ClientRepresentation();
    client.setClientId("new-client");
    Response response = KeycloakUtils.getKeycloakInstanceBuAdminAccount().realm(KeycloakConstants.realm).clients().create(client);
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
    Response response = KeycloakUtils.getKeycloakInstanceBuAdminAccount().realm(KeycloakConstants.realm).users().create(user);
    System.out.println(response.getStatus());
  }


}
