package com.imdb.user_register.models.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Email
    private String email;

    @NotBlank
    @Size(min = 8,max = 20,message = "Password should contain at least 8, maximum 20 characters")
    private String password;

    private String firstName;

    private String lastName;

    @NotBlank
    private String username;

}
