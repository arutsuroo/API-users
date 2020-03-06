package com.wipro.api.users.create;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UsersCreateResponse {

    @ApiModelProperty(example = "12")
    private Long id;
}
