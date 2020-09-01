package com.rs.notedown.controllers;

import com.rs.notedown.models.Category;
import com.rs.notedown.models.Note;
import com.rs.notedown.payloads.ApiErrorResponse;
import com.rs.notedown.payloads.ApiSuccessResponse;
import com.rs.notedown.payloads.NoteCreateRequest;
import com.rs.notedown.payloads.NoteUpdateRequest;
import com.rs.notedown.services.AppUserService;
import com.rs.notedown.services.CategoryService;
import com.rs.notedown.services.NoteService;
import com.rs.notedown.utils.ModelMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
class CategoryNotes {
    private Category category;
    private List<Note> notes;
}

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/note")
@SuppressWarnings({"UnusedDeclaration"})
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AppUserService appUserService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER') AND @noteService.isSameGroup(#id)")
    public ResponseEntity<Object> getNoteById(@Valid @PathVariable long id) {
        try {
            return new ApiSuccessResponse(noteService.getById(id));
        } catch (Exception e) {
            return new ApiErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/my-all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Object> getAllNotesOfMineByCategories(Principal principal) {
        try {
            List<CategoryNotes> categoryNotes = categoryService.getAllByCreatedUser
                    (appUserService.getByUsername(principal.getName()).getId()).stream()
                    .filter(Objects::nonNull)
                    .map(category -> new CategoryNotes(category, noteService.getAllByCategory(category.getId())))
                    .collect(Collectors.toList());
            return new ApiSuccessResponse(categoryNotes);
        } catch (Exception e) {
            return new ApiErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/my-group-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllNotesOfGroupOfMine(Principal principal) {
        try {
            List<CategoryNotes> categoryNotes = categoryService.getAllByGroupName
                    (appUserService.getByUsername(principal.getName()).getGroupName()).stream()
                    .filter(Objects::nonNull)
                    .map(category -> new CategoryNotes(category, noteService.getAllByCategory(category.getId())))
                    .collect(Collectors.toList());
            return new ApiSuccessResponse(categoryNotes);
        } catch (Exception e) {
            return new ApiErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-category/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER') AND @categoryService.isSameGroup(#id)")
    public ResponseEntity<Object> getNotesByCategory(@Valid @PathVariable long id) {
        try {
            return new ApiSuccessResponse(noteService.getAllByCategory(id));
        } catch (Exception e) {
            return new ApiErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Object> createNote
            (@Valid @RequestBody NoteCreateRequest noteCreateRequest, Principal principal) {
        try {
            Note note = (Note) ModelMap.convert(noteCreateRequest, Note.class);
            note.setCreatedBy(appUserService.getByUsername(principal.getName()));
            noteService.save(note);
            return new ApiSuccessResponse("Note creation succeeded");
        } catch (Exception e) {
            return new ApiErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER') AND @noteService.isMine(#id)")
    public ResponseEntity<Object> updateNote(
            @Valid @PathVariable long id,
            @Valid @RequestBody NoteUpdateRequest noteUpdateRequest, Principal principal) {
        try {
            Note storedNote = noteService.getById(id);
            Note note = (Note) ModelMap.convert(noteUpdateRequest, Note.class);
            note.setId(id);
            note.setCreatedBy(storedNote.getCreatedBy());
            note.setCategory(storedNote.getCategory());
            noteService.save(note);
            return new ApiSuccessResponse("Note updating succeeded");
        } catch (Exception e) {
            return new ApiErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("(hasRole('ADMIN') AND @noteService.isSameGroup(#id)) OR " +
            "(hasRole('USER') AND @noteService.isMine(#id))")
    public ResponseEntity<Object> deleteNote(@Valid @PathVariable long id, Principal principal) {
        try {
            noteService.delete(id);
            return new ApiSuccessResponse("Note deletion succeeded");
        } catch (Exception e) {
            return new ApiErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/by-category/{id}")
    @PreAuthorize("hasRole('ADMIN') AND @noteService.isSameGroup(#id)")
    public ResponseEntity<Object> deleteNotesByCategory(@Valid @PathVariable long id, Principal principal) {
        try {
            noteService.deleteByCategory(id);
            return new ApiSuccessResponse("Notes deletion succeeded");
        } catch (Exception e) {
            return new ApiErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
