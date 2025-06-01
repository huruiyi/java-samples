package com.savindu.user_management.service;


import com.savindu.user_management.response.Role;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface RoleService {

    void assignRole(String userId,String roleName);
    public List<Role> getRoles();
    public Role getRole( String roleName);
    public Response createRole(Role role);
    public Response deleteUser( String roleName);
    public Response updateRole(Role role);
}
