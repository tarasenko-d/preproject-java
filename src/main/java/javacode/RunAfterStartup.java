package javacode;

import javacode.model.Role;
import javacode.model.RolesEnum;
import javacode.model.User;
import javacode.service.RoleService;
import javacode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class RunAfterStartup {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("I am running........");
        Role[] roles = new Role[]{new Role(1L, RolesEnum.ROLE_ADMIN),new Role(2L,RolesEnum.ROLE_USER)};
        User admin = new User("admin", "admin", 18, "258874852");
        User user = new User("user", "user", 18, "354400995");

        for (Role role : roles) {
                roleService.addRoleApp(role);
        }

        if (!userService.userExists("admin")) {
            admin.setRoles(Collections.singleton(roleService.getRole(RolesEnum.ROLE_ADMIN)));
            System.out.println(admin);
            userService.addWithRole(admin);
        }

        if (!userService.userExists("user")) {
            user.setRoles(Collections.singleton(roleService.getRole(RolesEnum.ROLE_USER)));
            System.out.println(user);
            userService.addWithRole(user);
        }

        System.out.println("Run after startup end.");
    }
}

