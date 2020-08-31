package com.rs.notedown.utils;

import com.rs.notedown.constants.RoleName;
import com.rs.notedown.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SanitizedRole {
    private long id;
    private RoleName roleName;

    public SanitizedRole(Role role) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
    }
}
