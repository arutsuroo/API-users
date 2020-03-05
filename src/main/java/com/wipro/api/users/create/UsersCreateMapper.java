package com.wipro.api.users.create;

import com.wipro.domain.users.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersCreateMapper {

    UsersCreateResponse toUsersDto(User user);

    User toUsers(UsersCreateRequest user);

}
