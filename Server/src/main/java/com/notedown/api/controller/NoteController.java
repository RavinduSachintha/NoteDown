package com.notedown.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.notedown.api.model.Note;
import com.notedown.api.service.NoteService;

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class NoteController {

	private NoteService noteService;

	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}

	// get all the notes
	@GetMapping("")
	public ResponseEntity<List<Note>> getAllNotes() {
		List<Note> allNotes = noteService.findAll();
		if (allNotes == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else if (allNotes.isEmpty())
			return new ResponseEntity<>(allNotes, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(allNotes, HttpStatus.OK);
	}

	// get the note by id
	@GetMapping("/{id}")
	public ResponseEntity<Note> getNote(@PathVariable Long id) {
		Note note = noteService.findById(id);
		if (note == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(note, HttpStatus.OK);
	}

	@GetMapping("/by-category-id/{categoryIds}")
	public ResponseEntity<List<Note>> getAllNotesByCategoryId(@PathVariable Long[] categoryIds) {
		List<Note> allNotesByCategoryId = new ArrayList<>();
		for (Long categoryId : categoryIds) {
			List<Note> notesByCategoryId = noteService.findByCategoryId(categoryId);
			if (!notesByCategoryId.isEmpty())
				allNotesByCategoryId.addAll(notesByCategoryId);
		}
		if (allNotesByCategoryId.isEmpty())
			return new ResponseEntity<>(allNotesByCategoryId, HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(allNotesByCategoryId, HttpStatus.OK);
	}

	// save the note
	@PostMapping("")
	public ResponseEntity<Note> saveNote(@RequestBody @Valid Note note, BindingResult bindingResult,
			UriComponentsBuilder uriComponentsBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (note == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}

		noteService.save(note);
		headers.setLocation(uriComponentsBuilder.path("/notes/{id}").buildAndExpand(note.getId()).toUri());
		return new ResponseEntity<>(note, headers, HttpStatus.CREATED);

	}

	// update the note
	@PutMapping("/{id}")
	public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody @Valid Note note,
			BindingResult bindingResult) {
		Note currentNote = noteService.findById(id);
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (note == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}
		if (currentNote == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		noteService.update(note);
		return new ResponseEntity<>(note, HttpStatus.NO_CONTENT);
	}

	// delete the note
	@DeleteMapping("/{id}")
	public ResponseEntity<Note> deleteNote(@PathVariable("id") Long id) {
		Note noteToDelete = noteService.findById(id);
		if (noteToDelete == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		noteService.delete(id);
		return new ResponseEntity<>(noteToDelete, HttpStatus.NO_CONTENT);
	}
}
