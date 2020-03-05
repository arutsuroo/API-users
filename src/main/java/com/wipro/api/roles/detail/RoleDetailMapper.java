package com.wipro.api.roles.detail;

import com.wipro.domain.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleDetailMapper {
    RoleDetailResponse toRolesDto(Role role);
}
