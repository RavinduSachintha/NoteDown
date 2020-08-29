package com.rs.notedown.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rs.notedown.constants.RoleName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings({"UnusedDeclaration"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RoleName roleName;

    @OneToMany(mappedBy = "role")
    @JsonManagedReference
    private Set<AppUser> users = new HashSet<>();

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }
}
