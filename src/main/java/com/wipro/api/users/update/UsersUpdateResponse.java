package com.wipro.api.users.update;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsersUpdateResponse {
    private Long id;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

}
