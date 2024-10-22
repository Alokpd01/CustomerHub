package com.customerhub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;

    @NotEmpty(message = "First name should not be empty or null")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty or null")
    private String lastName;

    @NotEmpty(message = "Username should not be empty or null")
    private String username;

    @NotEmpty(message = "Password should not be empty or null")
    private String password;

    @NotEmpty(message = "Email should not be empty or null")
    @Email(message = "Email should be valid")
    private String email;

    private Long amount;

}
