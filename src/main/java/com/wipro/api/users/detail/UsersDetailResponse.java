package com.wipro.api.users.detail;

import com.wipro.api.users.common.UsersDto;
import lombok.Data;


import java.time.LocalDate;

@Data
public class UsersDetailResponse extends UsersDto {

    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private int idRole;
}
