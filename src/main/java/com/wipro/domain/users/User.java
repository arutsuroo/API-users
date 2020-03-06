package com.wipro.domain.users;

import com.wipro.domain.role.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, message = "Name should be at least 2 characters")
    @ApiModelProperty(notes = "Name should have at least 2 characters.")
    @NotNull
    private String userName;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Past
    @ApiModelProperty(notes = "Birth date should be in the past")
    private LocalDate birthDate;

    @NotNull
    private String email;

    @ManyToOne()
    private Role roles;

    @Override
    public String toString() {
        String name = firstName + " " + lastName;
        return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
    }
}
