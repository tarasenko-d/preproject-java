package javacode.service;

import javacode.model.Role;
import javacode.model.RolesEnum;

public interface RoleService {

    boolean roleExists(RolesEnum roleName);
    Role getRole(RolesEnum roleName);
    void addRole(RolesEnum roleName);
    void addRoleApp(Role role);
}
