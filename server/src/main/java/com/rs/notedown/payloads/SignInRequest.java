package com.rs.notedown.payloads;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {

  @NotBlank private String usernameOrEmail;

  @NotBlank private String password;
}
