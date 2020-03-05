package com.wipro.api.users.list;

import com.wipro.domain.users.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersListMapper {
    UsersListResponse toUsersDto(User user);
}
