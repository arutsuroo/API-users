package com.wipro.api.users.detail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UsersDetailRequest {

    @ApiModelProperty(example = "666")
    private Long id;

}
