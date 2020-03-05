package com.wipro.api.roles.create;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RoleCreateRequest {

    @Size(min = 2, message = "Role should be at least 2 characters")
    @ApiModelProperty(example = "Admin")
    @NotNull
    private String name;

}
