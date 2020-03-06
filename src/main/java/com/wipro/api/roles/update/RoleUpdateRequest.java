package com.wipro.api.roles.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class RoleUpdateRequest {

    @Size(min = 2, message = "Role should be at least 2 characters")
    @ApiModelProperty(notes = "Role should have at least 2 characters.", example = "Admin")
    private String name;
}
