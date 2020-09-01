package com.rs.notedown.repositories;

import com.rs.notedown.models.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings({"UnusedDeclaration"})
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByCategoryId(long categoryId);

    void deleteByCategoryId(long categoryId);
}
