package com.imdb.user_operations.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int userId;
    @NotBlank
    private String userName;

    @NotBlank
    @Email
    private String email;

    private String firstName;
    private String lastName;
}
