package com.rs.notedown.models;

import com.fasterxml.jackson.annotation.*;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings({"UnusedDeclaration"})
public class AppUser extends Base implements Serializable {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;

  @Column(nullable = false, length = 50) private String name;

  @Column(unique = true, nullable = false, updatable = false, length = 20)
  private String username;

  @Column(unique = true, nullable = false) private String email;

  @Column(nullable = false, length = 100) private String password;

  @Column(nullable = false, updatable = false, length = 20)
  private String groupName;

  @ManyToOne(optional = false) @JsonBackReference private Role role;

  @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
  @JsonManagedReference
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
                    property = "id")
  @JsonIdentityReference(alwaysAsId = true)
  private Set<Category> categories;

  @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
  @JsonManagedReference
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
                    property = "id")
  @JsonIdentityReference(alwaysAsId = true)
  private Set<Note> notes;

  public AppUser(String name, String username, String email, String password,
                 String groupName, Role role) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.groupName = groupName;
    this.role = role;
  }
}
