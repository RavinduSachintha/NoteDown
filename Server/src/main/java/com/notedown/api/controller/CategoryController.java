package com.notedown.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.notedown.api.model.Category;
import com.notedown.api.service.CategoryService;

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> allCategories = categoryService.findAll();
		if (allCategories == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else if (allCategories.isEmpty())
			return new ResponseEntity<>(allCategories, HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(allCategories, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category, BindingResult bindingResult,
			UriComponentsBuilder uriComponentsBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (category == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}

		categoryService.save(category);
		headers.setLocation(uriComponentsBuilder.path("/notes/{id}").buildAndExpand(category.getId()).toUri());
		return new ResponseEntity<>(category, headers, HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody @Valid Category category,
			BindingResult bindingResult) {
		Category currentCategory = categoryService.findById(id);
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (category == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}
		if (currentCategory == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		categoryService.update(category);
		return new ResponseEntity<>(category, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
		Category categoryToDelete = categoryService.findById(id);
		if (categoryToDelete == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		categoryService.delete(id);
		return new ResponseEntity<>(categoryToDelete, HttpStatus.NO_CONTENT);
	}

}
