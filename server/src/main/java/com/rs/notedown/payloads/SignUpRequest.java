package com.rs.notedown.payloads;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpRequest {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 4, max = 50, message = "Name must have 4-50 character length")
    private String name;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, max = 20, message = "Username must have 4-20 character length")
    private String username;

    @Email(message = "Email validation failed")
    @NotBlank(message = "Email is mandatory")
    @Size(max = 255, message = "Email must be shorter than 255 characters")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 100, message = "Password must have 6-100 character length")
    private String password;
}
