package javacode.mapper;


import javacode.dto.UserDto;
import javacode.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {RoleMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

   UserDto userToUserDto(User user);

   User userDtoToUser(UserDto userDto);



}
