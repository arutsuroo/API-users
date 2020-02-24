package com.wipro.api.users.common.UsersMapper;

import com.wipro.api.users.common.UsersDto;
import com.wipro.domain.users.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersDto toUserDto(User user);

    User fromDto(UsersDto user);
}
