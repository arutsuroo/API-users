package com.wipro.api.users.common.UsersMapper;

import com.wipro.api.users.common.UsersDto;
import com.wipro.domain.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersMapper user = Mappers.getMapper(UsersMapper.class);
    UsersDto toUserDto(User user);

    User fromDto(UsersDto user);
}
