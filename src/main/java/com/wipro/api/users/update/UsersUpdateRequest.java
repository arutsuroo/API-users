package com.wipro.api.users.update;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UsersUpdateRequest {

    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private int idRole;

}
