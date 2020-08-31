package com.rs.notedown.repositories;

import com.rs.notedown.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings({"UnusedDeclaration"})
public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByCreatedByGroupName(String groupName);

    List<Category> findByCreatedById(long id);
}
