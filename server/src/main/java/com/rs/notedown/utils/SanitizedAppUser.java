package com.rs.notedown.utils;

import com.rs.notedown.models.AppUser;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SanitizedAppUser {
    private long id;
    private String name;
    private String username;
    private String email;
    private String groupName;
    private SanitizedRole role;
    private Date createdAt;
    private Date updatedAt;

    public SanitizedAppUser(AppUser appUser) {
        this.id = appUser.getId();
        this.name = appUser.getName();
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
        this.groupName = appUser.getGroupName();
        this.role = new SanitizedRole(appUser.getRole());
        this.createdAt = appUser.getCreatedAt();
        this.updatedAt = appUser.getUpdatedAt();
    }
}
