package com.rs.notedown.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponse {

    private String accessToken;
    private final String tokenType = "Bearer";

    public SignInResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
