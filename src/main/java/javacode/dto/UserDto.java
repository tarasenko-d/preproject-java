package javacode.dto;

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
    private String profileId;
    private String profileImg;
    private Set<RoleDto> roles;


}
