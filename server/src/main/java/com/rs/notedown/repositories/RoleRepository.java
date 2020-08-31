package com.rs.notedown.repositories;

import com.rs.notedown.constants.RoleName;
import com.rs.notedown.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@SuppressWarnings({"UnusedDeclaration"})
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
