package com.rs.notedown.repositories;

import com.rs.notedown.models.AppUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings({"UnusedDeclaration"})
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
  Optional<AppUser> findByUsername(String username);

  Optional<AppUser> findByUsernameOrEmail(String username, String email);

  Optional<AppUser> findByUsernameOrEmailOrGroupName(String username,
                                                     String email,
                                                     String groupName);

  List<AppUser> findByGroupName(String groupName);
}
