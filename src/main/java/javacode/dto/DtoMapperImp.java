package javacode.dto;

import javacode.dao.RoleDao;
import javacode.model.Role;
import javacode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class DtoMapperImp implements DtoMapper {

    @Autowired
    private RoleDao roleDao;


    @Override
    public UserDto mapToDto(User user) {
        Set<Role> roleSet = user.getRoles();
        String roleDto="ROLE_USER";
        for (Role role : roleSet) {
            if (role.getName().equals("ROLE_ADMIN")){
                roleDto=role.getName();
            }
        }
        UserDto userDto = new UserDto(user.getId(), user.getLogin(), user.getPassword(), user.getAge(), roleDto);
        return userDto;
    }

    @Override
    public User mapToUser(UserDto userDto) {
        User user = new User(userDto.getId(), userDto.getLogin(), userDto.getPassword(), userDto.getAge(), null,null);
        user.setRoles(Collections.singleton(roleDao.findRoleByName(userDto.getRole())));
        System.out.println(user);
        return user;
    }
}
