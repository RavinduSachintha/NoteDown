package com.rs.notedown.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "Name is mandatory")
    @Size(max = 50, message = "Name must be shorter than 50 characters")
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Username is mandatory")
    @Size(max = 20, message = "Username must be shorter than 20 characters")
    @Column(unique = true, nullable = false, updatable = false, length = 20)
    private String username;

    @Email(message = "Email validation failed")
    @NotBlank(message = "Email is mandatory")
    @Size(max = 255, message = "Email must be shorter than 255 characters")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(max = 100, message = "Password must be shorter than 100 characters")
    @Column(nullable = false, length = 100)
    private String password;

    @NotNull(message = "User role is mandatory")
    @ManyToOne
    @JsonBackReference
    private Role role;

    public AppUser(String name, String username, String email, String password, Role role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
