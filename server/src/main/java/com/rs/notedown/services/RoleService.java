package com.rs.notedown.services;

import com.rs.notedown.constants.RoleName;
import com.rs.notedown.exceptions.DataNotExistException;
import com.rs.notedown.models.Role;
import com.rs.notedown.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"UnusedDeclaration"})
public class RoleService {
  @Autowired private RoleRepository roleRepository;

  public Role getById(long id) {
    return roleRepository.findById(id).orElseThrow(
        () -> new DataNotExistException("Role", id));
  }

  public Role getByName(RoleName roleName) {
    return roleRepository.findByRoleName(roleName).orElseThrow(
        () -> new DataNotExistException("Role", roleName.name()));
  }
}
