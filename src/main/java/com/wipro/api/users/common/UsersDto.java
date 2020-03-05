package com.wipro.api.users.common;

import com.wipro.domain.role.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ApiModel(description = "All details about the user.")
public class UsersDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, message = "Username should be at least 2 characters")
    @ApiModelProperty(notes = "Username should have at least 2 characters.")
    @NotNull
    private String userName;

    @Size(min = 2, message = "First Name should be at least 2 characters")
    @ApiModelProperty(notes = "First Name should have at least 2 characters.")
    @NotNull
    private String firstName;

    @Size(min = 2, message = "Last Name should be at least 2 characters")
    @ApiModelProperty(notes = "Last Name should have at least 2 characters.")
    @NotNull
    private String lastName;

    @NotNull
    @Past
    @ApiModelProperty(notes = "Birth date should be in the past")
    private LocalDate birthDate;

    @NotNull
    @Email
    @ApiModelProperty(notes = "Email format invalid")
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    private Role roles;

    public UsersDto() {}

    public UsersDto(String userName, String firstName, String lastName, LocalDate birthDate, String email, Role roles) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}
