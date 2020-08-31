package com.rs.notedown.services;

import com.rs.notedown.exceptions.DataNotExistException;
import com.rs.notedown.models.AppUser;
import com.rs.notedown.models.Category;
import com.rs.notedown.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings({"UnusedDeclaration"})
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AppUserService appUserService;

    public Category getById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new DataNotExistException("Category", id));
    }

    public List<Category> getAllByGroupName(String groupName) {
        return categoryRepository.findByCreatedByGroupName(groupName);
    }

    public List<Category> getAllByCreatedUser(long userId) {
        return categoryRepository.findByCreatedById(userId);
    }

    public boolean isSameGroup(long categoryId) {
        AppUser currentUser = appUserService.getByUsername
                (SecurityContextHolder.getContext().getAuthentication().getName());
        return this.getById(categoryId).getCreatedBy().getGroupName().equals(currentUser.getGroupName());
    }

    public boolean isMine(long categoryId) {
        AppUser currentUser = appUserService.getByUsername
                (SecurityContextHolder.getContext().getAuthentication().getName());
        return this.getById(categoryId).getCreatedBy().getId() == currentUser.getId();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(long id) {
        categoryRepository.deleteById(id);
    }
}
