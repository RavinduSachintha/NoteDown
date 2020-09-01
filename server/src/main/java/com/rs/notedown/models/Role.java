package com.rs.notedown.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.rs.notedown.constants.RoleName;
import java.util.HashSet;
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
public class Role {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) private long id;

  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false, length = 20)
  private RoleName roleName;

  @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
  @JsonManagedReference
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
                    property = "id")
  @JsonIdentityReference(alwaysAsId = true)
  private Set<AppUser> users = new HashSet<>();

  public Role(RoleName roleName) { this.roleName = roleName; }
}
