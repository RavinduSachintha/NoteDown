package com.rs.notedown.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponse {

  private final String tokenType = "Bearer";
  private String accessToken;

  public SignInResponse(String accessToken) { this.accessToken = accessToken; }
}
