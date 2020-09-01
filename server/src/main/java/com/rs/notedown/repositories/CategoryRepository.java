package com.rs.notedown.repositories;

import com.rs.notedown.models.Category;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings({"UnusedDeclaration"})
public interface CategoryRepository extends CrudRepository<Category, Long> {
  List<Category> findByCreatedByGroupName(String groupName);

  List<Category> findByCreatedById(long id);
}
