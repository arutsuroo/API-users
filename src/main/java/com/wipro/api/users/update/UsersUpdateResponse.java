package com.wipro.api.users.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UsersUpdateResponse {
    @ApiModelProperty(example = "60")
    private Long id;
}
