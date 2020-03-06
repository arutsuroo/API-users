package com.wipro.api.roles.detail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleDetailRequest {

    @ApiModelProperty(example = "2")
    private Long id;

    @ApiModelProperty(example = "Default")
    private String name;

}
