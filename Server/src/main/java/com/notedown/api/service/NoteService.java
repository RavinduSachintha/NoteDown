package com.notedown.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notedown.api.model.Note;
import com.notedown.api.repository.NoteRepository;

@Service
@Transactional
public class NoteService {

	private NoteRepository noteRepository;

	public NoteService() {
	}

	@Autowired
	public NoteService(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	public List<Note> findAll() {
		return noteRepository.findAll();
	}

	public Note findById(Long id) {
		return noteRepository.findOne(id);
	}

	public Note findByTitle(String title) {
		return noteRepository.findByTitleAllIgnoreCase(title);
	}

	public List<Note> findByCategoryId(Long id) {
		return noteRepository.findByCategoryId(id);
	}

	public void save(Note note) {
		noteRepository.save(note);
	}

	public void delete(Long id) {
		noteRepository.delete(id);
	}

	public void update(Note note) {
		noteRepository.save(note);
	}

}
