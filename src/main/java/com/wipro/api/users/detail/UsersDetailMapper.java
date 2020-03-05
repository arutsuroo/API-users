package com.wipro.api.users.detail;

import com.wipro.domain.users.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersDetailMapper {
    UsersDetailResponse toUsersDto(User user);
}
