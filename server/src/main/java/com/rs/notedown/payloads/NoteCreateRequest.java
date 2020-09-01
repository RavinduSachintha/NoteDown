package com.rs.notedown.payloads;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteCreateRequest extends NoteUpdateRequest {

  @NotNull(message = "Category is mandatory") private long categoryId;
}
