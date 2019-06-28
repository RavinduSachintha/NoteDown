package com.notedown.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Note {
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String title;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Lob
	private String description;

	// Constructors
	@Autowired
	public Note(Long id, String title, Category category, String description) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.description = description;
	}

	public Note() {

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
