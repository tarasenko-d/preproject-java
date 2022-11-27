package javacode.mapper;

import java.util.HashSet;
import java.util.Set;
import javacode.dto.RoleDto;
import javacode.dto.UserDto;
import javacode.model.Role;
import javacode.model.RolesEnum;
import javacode.model.User;
import javax.annotation.processing.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-27T02:44:47+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
public class UserMapperImpl implements UserMapper {

    private final RoleMapper roleMapper = Mappers.getMapper( RoleMapper.class );

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setLogin( user.getLogin() );
        userDto.setPassword( user.getPassword() );
        userDto.setAge( user.getAge() );
        userDto.setProfileId( user.getProfileId() );
        userDto.setProfileImg( user.getProfileImg() );
        userDto.setRoles( roleMapper.rolesToRolesDTO( user.getRoles() ) );

        return userDto;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setLogin( userDto.getLogin() );
        user.setPassword( userDto.getPassword() );
        user.setAge( userDto.getAge() );
        user.setProfileId( userDto.getProfileId() );
        user.setProfileImg( userDto.getProfileImg() );
        user.setRoles( roleDtoSetToRoleSet( userDto.getRoles() ) );

        return user;
    }

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        if ( roleDto.getName() != null ) {
            role.setName( Enum.valueOf( RolesEnum.class, roleDto.getName() ) );
        }

        return role;
    }

    protected Set<Role> roleDtoSetToRoleSet(Set<RoleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new HashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDto roleDto : set ) {
            set1.add( roleDtoToRole( roleDto ) );
        }

        return set1;
    }
}
