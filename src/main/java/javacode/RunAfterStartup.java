package javacode;

import javacode.model.User;
import javacode.service.RoleService;
import javacode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class RunAfterStartup {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("I am running........");

        String[] roles = new String[]{"ROLE_ADMIN","ROLE_USER"};
        User admin = new User("admin", "admin", 18);
        User user = new User("user", "user", 18);

        for (String role : roles) {
            if (roleService.roleExists(role)) {
                System.out.println("Role "+role+" doesnt exist before");
                roleService.addRole(role);
            }
        }

        if (!userService.userExists("admin")) {
            admin.setRoles(Collections.singleton(roleService.getRole("ROLE_ADMIN")));
            System.out.println(admin);
            userService.addWithRole(admin);
        }

        if (!userService.userExists("user")) {
            user.setRoles(Collections.singleton(roleService.getRole("ROLE_USER")));
            System.out.println(user);
            userService.addWithRole(user);
        }

        System.out.println("Run after startup end.");
    }
}

