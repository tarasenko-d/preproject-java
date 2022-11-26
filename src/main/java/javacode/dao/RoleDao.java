package javacode.dao;

import javacode.model.Role;
import javacode.model.RolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role findRoleByName(RolesEnum name);

    boolean existsRolesByName(RolesEnum name);

}