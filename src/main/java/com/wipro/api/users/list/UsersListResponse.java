package com.wipro.api.users.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UsersListResponse {
    @ApiModelProperty(example = "31")
    private Long id;
}
