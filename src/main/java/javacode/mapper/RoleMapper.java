package javacode.mapper;

import javacode.dto.RoleDto;
import javacode.model.Role;
import javacode.model.RolesEnum;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import javax.persistence.EnumType;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Named("roleDtoToRole")
    default Role roleDtoToRole(RoleDto roleDto) {
        Role role = new Role(RolesEnum.valueOf(roleDto.getName()));
        return role;
    }

    //@Mapping(target = "id", ignore = true)
    @Named("roleToRoleDto")
    default RoleDto roleToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto(role.getName().toString());
        return roleDto;
    }

    @IterableMapping(qualifiedByName = "roleToRoleDto")
    Set<RoleDto> rolesToRolesDTO(Set<Role> role);


}
