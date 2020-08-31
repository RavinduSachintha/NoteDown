package com.rs.notedown.payloads;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryCreateRequest {

    @NotBlank(message = "Title is mandatory")
    @Size(min = 1, max = 50, message = "Title must shorter than 50 characters")
    private String title;

}
