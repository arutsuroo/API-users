package com.wipro.api.users.update;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UsersUpdateRequest {

    @ApiModelProperty(example = "cerebro")
    private String userName;

    @ApiModelProperty(example = "Charles")
    private String firstName;

    @ApiModelProperty(example = "Xavier")
    private String lastName;

    @ApiModelProperty(example = "1926-04-12")
    private LocalDate birthDate;

    @ApiModelProperty(example = "xavier@xmen.com")
    private String email;

    @ApiModelProperty(example = "1")
    private int idRole;

}
