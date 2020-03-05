package com.wipro.api.roles.create;

import com.wipro.domain.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleCreateMapper {

    RoleCreateResponse toRoleDto(Role role);

    Role toRoles(RoleCreateRequest role);

}
