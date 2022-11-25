package javacode.service;

import javacode.model.Role;

public interface RoleService {

    boolean roleExists(String roleName);
    Role getRole(String roleName);
    void addRole(String roleName);
}
