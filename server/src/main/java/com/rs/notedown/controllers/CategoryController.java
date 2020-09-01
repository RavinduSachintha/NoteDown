package com.rs.notedown.controllers;

import com.rs.notedown.models.Category;
import com.rs.notedown.payloads.ApiErrorResponse;
import com.rs.notedown.payloads.ApiSuccessResponse;
import com.rs.notedown.payloads.CategoryCreateRequest;
import com.rs.notedown.payloads.CategoryUpdateRequest;
import com.rs.notedown.services.AppUserService;
import com.rs.notedown.services.CategoryService;
import com.rs.notedown.utils.ModelMap;
import java.security.Principal;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/category")
@SuppressWarnings({"UnusedDeclaration"})
public class CategoryController {
  @Autowired private CategoryService categoryService;
  @Autowired private AppUserService appUserService;

  @GetMapping("/{id}")
  @PreAuthorize(
      "hasAnyRole('ADMIN','USER') AND @categoryService.isSameGroup(#id)")
  public ResponseEntity<Object>
  getCategoryById(@Valid @PathVariable long id) {
    try {
      return new ApiSuccessResponse(categoryService.getById(id));
    } catch (Exception e) {
      return new ApiErrorResponse(e.getMessage(),
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/my-all")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public ResponseEntity<Object> getAllCategoriesOfMine(Principal principal) {
    try {
      return new ApiSuccessResponse(categoryService.getAllByCreatedUser(
          appUserService.getByUsername(principal.getName()).getId()));
    } catch (Exception e) {
      return new ApiErrorResponse(e.getMessage(),
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/my-group-all")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Object>
  getAllCategoriesOfGroupOfMine(Principal principal) {
    try {
      return new ApiSuccessResponse(categoryService.getAllByGroupName(
          appUserService.getByUsername(principal.getName()).getGroupName()));
    } catch (Exception e) {
      return new ApiErrorResponse(e.getMessage(),
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/create")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public ResponseEntity<Object> createCategory(
      @Valid @RequestBody CategoryCreateRequest categoryCreateRequest,
      Principal principal) {
    try {
      Category category =
          (Category)ModelMap.convert(categoryCreateRequest, Category.class);
      category.setCreatedBy(appUserService.getByUsername(principal.getName()));
      categoryService.save(category);
      return new ApiSuccessResponse("Category creation succeeded");
    } catch (Exception e) {
      return new ApiErrorResponse(e.getMessage(),
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/update/{id}")
  @PreAuthorize("(hasRole('ADMIN') AND @categoryService.isSameGroup(#id)) OR "
                + "(hasRole('USER') AND @categoryService.isMine(#id))")
  public ResponseEntity<Object>
  updateCategory(@Valid @PathVariable long id,
                 @Valid
                 @RequestBody CategoryUpdateRequest categoryUpdateRequest,
                 Principal principal) {
    try {
      Category storedCategory = categoryService.getById(id);
      Category category =
          (Category)ModelMap.convert(categoryUpdateRequest, Category.class);
      category.setId(id);
      category.setCreatedBy(storedCategory.getCreatedBy());
      category.setNotes(storedCategory.getNotes());
      categoryService.save(category);
      return new ApiSuccessResponse("Category updating succeeded");
    } catch (Exception e) {
      return new ApiErrorResponse(e.getMessage(),
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/delete/{id}")
  @PreAuthorize("(hasRole('ADMIN') AND @categoryService.isSameGroup(#id)) OR "
                + "(hasRole('USER') AND @categoryService.isMine(#id))")
  public ResponseEntity<Object>
  deleteCategory(@Valid @PathVariable long id, Principal principal) {
    try {
      categoryService.delete(id);
      return new ApiSuccessResponse("Category deletion succeeded");
    } catch (Exception e) {
      return new ApiErrorResponse(e.getMessage(),
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
