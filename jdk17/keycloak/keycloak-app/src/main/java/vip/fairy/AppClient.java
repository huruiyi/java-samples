package vip.fairy;

import java.util.List;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.ClientRepresentation;

public class AppClient {


  public static void main(String[] args) {
    RealmResource realmResource = KeycloakUtils.getKeycloakInstanceByServiceAccount().realm(KeycloakConstants.realm);
    List<ClientRepresentation> clientRepresentations = realmResource.clients().findByClientId(KeycloakConstants.clientId);
    for (ClientRepresentation client : clientRepresentations) {
      System.out.println("Id: " + client.getId());
      System.out.println("Client ID: " + client.getClientId());
      System.out.println("Client Name: " + client.getName());
      System.out.println("Client Secret: " + client.getSecret());
    }
    //client.getId()
    ClientResource clientResource = realmResource.clients().get("9e7cd4ef-f76d-4fa1-a795-c11b11b71ec5");
    System.out.println( clientResource.toRepresentation());
  }
}
