package com.rs.notedown.services;

import com.rs.notedown.exceptions.DataNotExistException;
import com.rs.notedown.models.AppUser;
import com.rs.notedown.models.Note;
import com.rs.notedown.repositories.NoteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"UnusedDeclaration"})
public class NoteService {
  @Autowired private NoteRepository noteRepository;
  @Autowired private AppUserService appUserService;

  public Note getById(long id) {
    return noteRepository.findById(id).orElseThrow(
        () -> new DataNotExistException("Note", id));
  }

  public List<Note> getAllByCategory(long categoryId) {
    return noteRepository.findByCategoryId(categoryId);
  }

  public boolean isSameGroup(long id) {
    AppUser currentUser = appUserService.getByUsername(
        SecurityContextHolder.getContext().getAuthentication().getName());
    return this.getById(id).getCreatedBy().getGroupName().equals(
        currentUser.getGroupName());
  }

  public boolean isMine(long noteId) {
    AppUser currentUser = appUserService.getByUsername(
        SecurityContextHolder.getContext().getAuthentication().getName());
    return this.getById(noteId).getCreatedBy().getId() == currentUser.getId();
  }

  public void save(Note note) { noteRepository.save(note); }

  public void delete(long id) { noteRepository.deleteById(id); }

  public void deleteByCategory(long categoryId) {
    noteRepository.deleteByCategoryId(categoryId);
  }
}
