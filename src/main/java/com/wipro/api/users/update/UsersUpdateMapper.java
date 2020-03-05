package com.wipro.api.users.update;

import com.wipro.domain.users.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersUpdateMapper {

    UsersUpdateResponse toUsersDto(User user);

    User toUsers(UsersUpdateRequest user);

}
