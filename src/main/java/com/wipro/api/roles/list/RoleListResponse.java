package com.wipro.api.roles.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleListResponse {
    @ApiModelProperty(example = "2")
    private Long id;
}
