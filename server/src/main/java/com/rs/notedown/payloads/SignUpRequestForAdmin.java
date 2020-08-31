package com.rs.notedown.payloads;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpRequestForAdmin extends SignUpRequest {
    @NotBlank(message = "Group Name is mandatory")
    @Size(min = 4, max = 20, message = "Group Name must have 4-20 character length")
    private String groupName;
}
