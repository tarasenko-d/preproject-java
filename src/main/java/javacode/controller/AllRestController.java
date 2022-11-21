package javacode.controller;

import javacode.dto.DtoMapper;
import javacode.dto.UserDto;
import javacode.model.User;
import javacode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AllRestController {

    private final UserService userService;

    @Autowired
    private DtoMapper modelMapper;


    public AllRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list/rest")
    public List<UserDto> getList() {
        List<User> users = userService.listUser();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
        userDtoList.add(modelMapper.mapToDto(user));
        }
        return userDtoList;
    }

    @GetMapping("/kids/rest")
    public List<UserDto> kids(@RequestParam(value = "age") int age) {
        System.out.println(age);
        List<User> personList = userService.findAllKids(age);
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : personList) {
            System.out.println(user);
            userDtoList.add(modelMapper.mapToDto(user));
        }
        System.out.println("kid rest");
        return userDtoList;
    }

    @GetMapping(value = "/admin/edit/rest/{id}")
    public User editUser(@PathVariable(name = "id") Long id) {
        User userToEdit = userService.findById(id);
        System.out.println("Get edit: "+userToEdit);
        return userToEdit;
    }

    @PutMapping(value = "/admin/edit/rest")
    public User edit(@RequestBody UserDto userDto) {
        System.out.println("Put edit receive DTO: \n"+userDto);
        User user = modelMapper.mapToUser(userDto);
        System.out.println("Put edit receive: \n"+user);
        userService.edit(user);
        return user;
    }

    @GetMapping("/user/rest")
    public UserDto getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("User actual:\n"+user);
        UserDto userDto = modelMapper.mapToDto(user);
        return userDto;
    }

    @PostMapping(value = "/save/rest")
    public void add_post(@RequestBody UserDto userDto) {
        System.out.println("Post save receive DTO:\n"+userDto);
        User user = modelMapper.mapToUser(userDto);
        System.out.println("Post save receive:\n"+user);
        userService.add(user);
    }

    @DeleteMapping(value = "/admin/delete/rest/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        User userToDelete = userService.findById(id);
        System.out.println("delete rest admin");
        userService.delete(userToDelete);
    }
}
