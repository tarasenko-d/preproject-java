package javacode.mapper;

import javacode.dto.RoleDto;
import javacode.model.Role;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);


    Role roleDtoToRole(RoleDto roleDto);

    //@Mapping(target = "id", ignore = true)
    @Named("roleToRoleDto")
    RoleDto roleToRoleDto(Role role);

    @IterableMapping(qualifiedByName = "roleToRoleDto")
    Set<RoleDto> rolesToRolesDTO(Set<Role> role);

}
