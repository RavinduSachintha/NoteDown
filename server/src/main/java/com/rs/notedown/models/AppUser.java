package com.rs.notedown.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings({"UnusedDeclaration"})
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(unique = true, nullable = false, updatable = false, length = 20)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(unique = true, nullable = false, updatable = false, length = 20)
    private String groupName;

    @ManyToOne
    @JsonBackReference
    private Role role;

    public AppUser(String name, String username, String email, String password, String groupName, Role role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.groupName = groupName;
        this.role = role;
    }
}
