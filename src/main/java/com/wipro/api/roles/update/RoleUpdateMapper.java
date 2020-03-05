package com.wipro.api.roles.update;

import com.wipro.domain.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleUpdateMapper {

    RoleUpdateResponse toRolesDto(Role role);

    Role toRoles(RoleUpdateRequest user);
}
