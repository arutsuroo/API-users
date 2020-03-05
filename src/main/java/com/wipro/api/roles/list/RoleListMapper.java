package com.wipro.api.roles.list;

import com.wipro.domain.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleListMapper {
    RoleListResponse toRolesDto(Role role);
}
