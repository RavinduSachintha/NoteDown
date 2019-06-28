package com.notedown.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notedown.api.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findByTitleAllIgnoreCase(String title);
}
