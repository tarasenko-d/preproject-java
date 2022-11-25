package javacode.controller;

import javacode.dto.UserDto;
import javacode.mapper.UserMapper;
import javacode.model.User;
import javacode.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AllRestController {

    private final UserService userService;


    public AllRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list/rest")
    public List<UserDto> getList() {
        List<User> users = userService.listUser();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {

            UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);
            userDtoList.add(userDto);
            System.out.println(userDto);

        }
        return userDtoList;
    }

    @GetMapping("/kids/rest")
    public List<UserDto> kids(@RequestParam(value = "age") int age) {
        System.out.println(age);
        List<User> personList = userService.findAllKids(age);
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : personList) {
            UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);
            userDtoList.add(userDto);
            System.out.println(userDto);
        }
        System.out.println("kid rest");
        return userDtoList;
    }

    @GetMapping(value = "/admin/edit/rest/{id}")
    public UserDto editUser(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        UserDto userToEdit = UserMapper.INSTANCE.userToUserDto(user);
        System.out.println("Get edit: " + userToEdit);
        return userToEdit;
    }

    @PutMapping(value = "/admin/edit/rest")
    public void edit(@RequestBody UserDto userDto) {
        System.out.println("Put edit receive DTO: \n" + userDto);
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        System.out.println("Put edit receive: \n" + user);
        userService.edit(user);
    }

    @GetMapping("/user/rest")
    public UserDto getUser(Principal principal) {
        // User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        User user = (User) userService.loadUserByUsername(principal.getName());
        System.out.println("User actual:\n" + user);
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);
        System.out.println(userDto);
        return userDto;
    }

    @PostMapping(value = "/save/rest")
    public void add_post(@RequestBody UserDto userDto) {
        System.out.println("Post save receive DTO:\n" + userDto);
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        System.out.println("Post save receive:\n" + user);
        userService.add(user);
    }

    @DeleteMapping(value = "/admin/delete/rest/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        User userToDelete = userService.findById(id);
        System.out.println("delete rest admin");
        userService.delete(userToDelete);
    }
}

//TODO
/*  исправить на сет в дто
    маппер переделать на mapsruct
    роль исправить на enum
    посмотреть как работает шифрование, почему нельзя расшифровать
    rest naming
    cacheable
    vk api, token
*/