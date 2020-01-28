package com.wipro.domain.users;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class User {

    private @Id @GeneratedValue Long id;
    private String userName;
    private String firstName;
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String email;

    public User() {

    }

    public User(String userName, String firstName, String lastName, Date birthDate, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
    }
}
