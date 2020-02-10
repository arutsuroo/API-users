package com.wipro.api.users.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsersDto {

    private String userName;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String email;

    public UsersDto() {}

    public UsersDto(String userName, String firstName, String lastName, LocalDate birthDate, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
    }
}
