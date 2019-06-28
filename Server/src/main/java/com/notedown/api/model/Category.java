package com.notedown.api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Category {
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String title;

//	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//	private Set<Note> notes = new HashSet<>();

	// Constructors
	public Category(Long id, String title) {
		this.id = id;
		this.title = title;
//		this.notes = notes;
	}

	public Category() {
	}

	public Category(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	public Set<Note> getNotes() {
//		return notes;
//	}
//
//	public void setNotes(Set<Note> notes) {
//		this.notes = notes;
//	}

}
