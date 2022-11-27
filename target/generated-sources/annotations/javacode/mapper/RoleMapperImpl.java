package javacode.mapper;

import java.util.HashSet;
import java.util.Set;
import javacode.dto.RoleDto;
import javacode.model.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-27T03:08:32+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Set<RoleDto> rolesToRolesDTO(Set<Role> role) {
        if ( role == null ) {
            return null;
        }

        Set<RoleDto> set = new HashSet<RoleDto>( Math.max( (int) ( role.size() / .75f ) + 1, 16 ) );
        for ( Role role1 : role ) {
            set.add( roleToRoleDto( role1 ) );
        }

        return set;
    }
}
