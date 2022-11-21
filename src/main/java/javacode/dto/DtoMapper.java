package javacode.dto;

import javacode.model.User;

public interface DtoMapper {

    UserDto mapToDto(User user);

    User mapToUser(UserDto userDto);

}
