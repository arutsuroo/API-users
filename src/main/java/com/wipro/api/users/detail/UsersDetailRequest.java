package com.wipro.api.users.detail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UsersDetailRequest {

    @ApiModelProperty(example = "666")
    private Long id;

    @ApiModelProperty(example = "doom_latveria")
    private String userName;

    @ApiModelProperty(example = "Victor")
    private String firstName;

    @ApiModelProperty(example = "Von Doom")
    private String lastName;

    @ApiModelProperty(example = "1928-09-01")
    private LocalDate birthDate;

    @ApiModelProperty(example = "doomesque@gmail.com.lv")
    private String email;

    @ApiModelProperty(example = "1")
    private int idRole;

}
