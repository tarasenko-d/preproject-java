package javacode.service;

import javacode.dao.RoleDao;
import javacode.model.Role;
import javacode.model.RolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Cacheable(cacheNames="roleExists")
    public boolean roleExists(RolesEnum roleName) {
        System.out.println("Role exists method");
        return roleDao.existsRolesByName(roleName);
    }

    @Override
    @Cacheable(cacheNames="roleExists")
    public Role getRole(RolesEnum roleName) {
        System.out.println("get role method");
        if (!roleExists(roleName)) {
            throw new NullPointerException("No such role in db");
        }
        return roleDao.findRoleByName(roleName);
    }

    @Override
    public void addRole(RolesEnum roleName) {
        if (!roleExists(roleName)) {
            System.out.println("Role "+roleName+" doesnt exists before");
            roleDao.save(new Role(roleName));
        }
    }

    @Override
    public void addRoleApp(Role role) {
        if (!roleExists(role.getName())) {
            roleDao.save(role);
            System.out.println("Role "+role+" added to db");
        }
    }
}
