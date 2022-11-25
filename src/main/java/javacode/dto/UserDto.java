package javacode.dto;

import javacode.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;
    private String login;
    private String password;
    private int age;
    private Set<RoleDto> roles;


}
