package com.notedown.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notedown.api.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
	Note findByTitleAllIgnoreCase(String title);
    List<Note> findByCategoryId(Long id);
}
