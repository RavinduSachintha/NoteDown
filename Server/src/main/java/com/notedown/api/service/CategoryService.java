package com.notedown.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notedown.api.model.Category;
import com.notedown.api.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(Long id) {
		return categoryRepository.findOne(id);
	}

	public Category findByName(String title) {
		return categoryRepository.findByTitleAllIgnoreCase(title);
	}

	public void save(Category category) {
		categoryRepository.save(category);
	}

	public void delete(Long id) {
		categoryRepository.delete(id);
	}

	public void update(Category category) {
		categoryRepository.save(category);
	}
}
