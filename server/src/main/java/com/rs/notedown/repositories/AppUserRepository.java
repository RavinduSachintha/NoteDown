package com.rs.notedown.repositories;

import com.rs.notedown.models.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings({"UnusedDeclaration"})
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
}