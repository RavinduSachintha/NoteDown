package com.rs.notedown.payloads;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NoteCreateRequest extends NoteUpdateRequest {

    @NotNull(message = "Category is mandatory")
    private long categoryId;

}
