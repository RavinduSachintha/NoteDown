package com.rs.notedown.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteUpdateRequest {

  @NotBlank(message = "Title is mandatory")
  @Size(min = 1, max = 50, message = "Title must shorter than 50 characters")
  private String title;

  @Size(max = 255, message = "Description must shorter than 255 characters")
  private String description;
}
