package javacode.service;

import javacode.dao.RoleDao;
import javacode.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Cacheable(cacheNames="roleExists")
    public boolean roleExists(String roleName) {
        System.out.println("Role exists method");
        return roleDao.existsRolesByName(roleName);
    }

    @Override
    @Cacheable(cacheNames="roleExists")
    public Role getRole(String roleName) {
        System.out.println("get role method");
        if (!roleExists(roleName)) {
            throw new NullPointerException("No such role in db");
        }
        return roleDao.findRoleByName(roleName);
    }

    @Override
    public void addRole(String roleName) {
        if (!roleExists(roleName)) {
            roleDao.save(new Role(roleName));
        }
    }
}
