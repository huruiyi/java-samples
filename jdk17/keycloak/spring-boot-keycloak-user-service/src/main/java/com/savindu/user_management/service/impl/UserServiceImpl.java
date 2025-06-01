package com.savindu.user_management.service.impl;


import com.savindu.user_management.response.UserRegistrationRecord;
import com.savindu.user_management.service.UserService;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Value("${keycloak.realm}")
  private String realm;

  private Keycloak keycloak;

  public UserServiceImpl(Keycloak keycloak) {
    this.keycloak = keycloak;
  }

  @Override
  public UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord) {
    UserRepresentation user = new UserRepresentation();
    user.setEnabled(true);
    user.setUsername(userRegistrationRecord.username());
    user.setEmail(userRegistrationRecord.email());
    user.setFirstName(userRegistrationRecord.firstName());
    user.setLastName(userRegistrationRecord.lastName());
    user.setEmailVerified(false);

    CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
    credentialRepresentation.setValue(userRegistrationRecord.password());
    credentialRepresentation.setTemporary(false);
    credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

    List<CredentialRepresentation> list = new ArrayList<>();
    list.add(credentialRepresentation);
    user.setCredentials(list);

    UsersResource usersResource = getUsersResource();

    Response response = usersResource.create(user);

    if (Objects.equals(201, response.getStatus())) {
      List<UserRepresentation> representationList = usersResource.searchByUsername(userRegistrationRecord.username(), true);
      if (!CollectionUtils.isEmpty(representationList)) {
        UserRepresentation userRepresentation1 = representationList.stream()
            .filter(userRepresentation -> Objects.equals(false, userRepresentation.isEmailVerified())).findFirst().orElse(null);
        assert userRepresentation1 != null;
        log.info("Email was send to user id {} ", userRepresentation1.getId());
        emailVerification(userRepresentation1.getId());
      }
      return userRegistrationRecord;
    } else {
      log.error("Error while creating user", response.getStatus());
    }

//        response.readEntity()

    return null;
  }

  private UsersResource getUsersResource() {
    RealmResource realm1 = keycloak.realm(realm);
    return realm1.users();
  }

  @Override
  public UserRepresentation getUserByIdOrName(String userId, String userName) {
    List<UserRepresentation> huruiyi = keycloak.realm(realm).users().searchByUsername(userName, true);
    if (!CollectionUtils.isEmpty(huruiyi)) {
      return huruiyi.get(0);
    }
    UserResource userResource = getUsersResource().get(userId);
    UserRepresentation user = userResource.toRepresentation();
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

  @Override
  public void deleteUserById(String userId) {
    getUsersResource().delete(userId);
  }


  @Override
  public void emailVerification(String userId) {
    UsersResource usersResource = getUsersResource();
    usersResource.get(userId).sendVerifyEmail();
  }

  @Override
  public UserResource getUserResource(String userId) {
    UsersResource usersResource = getUsersResource();
    return usersResource.get(userId);
  }

  @Override
  public void updatePassword(String userId) {

    UserResource userResource = getUserResource(userId);
    List<String> actions = new ArrayList<>();
    actions.add("UPDATE_PASSWORD");
    userResource.executeActionsEmail(actions);

  }

  @Override
  public void forgotPassword(String username) {
    UsersResource usersResource = getUsersResource();
    List<UserRepresentation> userRepresentations = usersResource.searchByUsername(username, true);
    UserRepresentation userRepresentation = userRepresentations.stream().findFirst().orElse(null);
    if (userRepresentation != null) {
      UserResource userResource = usersResource.get(userRepresentation.getId());
      List<String> actions = new ArrayList<>();
      actions.add("UPDATE_PASSWORD");
      userResource.executeActionsEmail(actions);
      return;
    }
    throw new RuntimeException("Username not found");

  }

  public void forgetPassword(String userId) {
    UserResource userResource = getUserResource(userId);
    List<String> actions = new ArrayList<>();
    actions.add("UPDATE_PASSWORD");
    userResource.executeActionsEmail(actions);
  }

}
