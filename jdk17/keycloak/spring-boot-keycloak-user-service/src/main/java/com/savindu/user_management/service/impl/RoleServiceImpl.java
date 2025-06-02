package com.savindu.user_management.service.impl;


import com.savindu.user_management.response.Role;
import com.savindu.user_management.service.RoleService;
import com.savindu.user_management.service.UserService;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.common.util.CollectionUtil;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  @Value("${keycloak.realm}")
  private String realm;

  private final Keycloak keycloak;

  private final UserService userService;

  @Override
  public void assignRole(String userId, String roleName) {
    UserResource userResource = userService.getUserResource(userId);
    RolesResource rolesResource = getRolesResource();
    RoleRepresentation representation = rolesResource.get(roleName).toRepresentation();
    userResource.roles().realmLevel().add(Collections.singletonList(representation));
  }

  @Override
  public List<Role> getRoles() {
    List<RoleRepresentation> roleRepresentations = keycloak.realm(realm).roles().list();
    return mapRoles(roleRepresentations);
  }

  @Override
  public Role getRole(String roleName) {

    return mapRole(keycloak.realm(realm).roles().get(roleName).toRepresentation());
  }

  @Override
  public Response createRole(Role role) {
    RoleRepresentation roleRep = mapRoleRep(role);
    keycloak.realm(realm).roles().create(roleRep);
    return Response.ok(role).build();
  }

  @Override
  public Response deleteUser(String roleName) {
    keycloak.realm(realm).roles().deleteRole(roleName);
    return Response.ok().build();
  }

  @Override
  public Response updateRole(Role role) {
    RoleRepresentation roleRep = mapRoleRep(role);
    roleRep.setDescription("desc test");
    keycloak.realm(realm).roles().get(role.getName()).update(roleRep);
    return Response.ok(role).build();
  }

  private RolesResource getRolesResource() {
    return keycloak.realm(realm).roles();
  }

  public static List<Role> mapRoles(List<RoleRepresentation> representations) {
    List<Role> roles = new ArrayList<>();
    if (CollectionUtil.isNotEmpty(representations)) {
      representations.forEach(roleRep -> roles.add(mapRole(roleRep)));
    }
    return roles;
  }

  public static Role mapRole(RoleRepresentation roleRep) {
    Role role = new Role();
    role.setId(roleRep.getId());
    role.setName(roleRep.getName());
    return role;
  }

  public RoleRepresentation mapRoleRep(Role role) {
    RoleRepresentation roleRep = new RoleRepresentation();
    roleRep.setName(role.getName());
    return roleRep;
  }


}
