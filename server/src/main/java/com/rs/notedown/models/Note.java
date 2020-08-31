package com.rs.notedown.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings({"UnusedDeclaration"})
public class Note extends Base implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column()
    private String description;

    @ManyToOne(optional = false)
    @JsonBackReference
    private Category category;

    @ManyToOne(optional = false)
    @JsonBackReference
    private AppUser createdBy;
}
