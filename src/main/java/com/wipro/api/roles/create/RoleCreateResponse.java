package com.wipro.api.roles.create;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleCreateResponse {
    @ApiModelProperty(example = "1")
    private Long id;
}
