package com.wipro.api.roles.detail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleDetailResponse {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Admin")
    private String name;
}
