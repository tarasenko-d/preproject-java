package javacode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    long id;
    String login;
    String password;
    int age;
  //  String passwordConfirm;
    String role;

}
