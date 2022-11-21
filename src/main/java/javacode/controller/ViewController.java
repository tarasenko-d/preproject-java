package javacode.controller;

import javacode.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final UserService userService;

    public ViewController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/list")
    public String getList() {
        return "index";
    }

    @GetMapping(value = "/admin/add")
    public String getAdd_admin() {
        return "save";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/kids")
    public String ages() {
        return "ages";
    }

    @GetMapping("/user")
    public String userGet() {
        return "user";
    }

    @GetMapping("/exit")
    public String exit() {
        return "exit";
    }
}
