package com.wipro.api.users.detail;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UsersDetailRequest {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private int idRole;

}
