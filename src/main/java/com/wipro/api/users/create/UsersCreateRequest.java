package com.wipro.api.users.create;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class UsersCreateRequest {

    @Size(min = 2, message = "Username should be at least 2 characters")
    @ApiModelProperty(example = "theDoctor", notes = "Username should have at least 2 characters.")
    @NotNull
    private String userName;

    @Size(min = 2, message = "First Name should be at least 2 characters")
    @ApiModelProperty(example = "John", notes = "First Name should have at least 2 characters.")
    @NotNull
    private String firstName;

    @Size(min = 2, message = "Last Name should be at least 2 characters")
    @ApiModelProperty(example = "Smith", notes = "Last Name should have at least 2 characters.")
    @NotNull
    private String lastName;

    @NotNull
    @Past
    @ApiModelProperty(example = "2000-05-05", notes = "Birth date should be in the past")
    private LocalDate birthDate;

    @NotNull
    @Email
    @ApiModelProperty(example = "doctor@tardis.com", notes = "Email format invalid")
    private String email;

    private int idRole;
}
