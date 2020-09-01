package com.rs.notedown.controllers;

import com.rs.notedown.constants.RoleName;
import com.rs.notedown.models.AppUser;
import com.rs.notedown.payloads.ApiErrorResponse;
import com.rs.notedown.payloads.ApiSuccessResponse;
import com.rs.notedown.payloads.SignUpRequest;
import com.rs.notedown.services.AppUserService;
import com.rs.notedown.services.RoleService;
import com.rs.notedown.utils.ModelMap;
import com.rs.notedown.utils.SanitizedAppUser;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/user")
@SuppressWarnings({"UnusedDeclaration"})
public class AppUserController {
  @Autowired private AppUserService appUserService;
  @Autowired private RoleService roleService;
  @Autowired private PasswordEncoder passwordEncoder;

  @PostMapping("/sign-up")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Object>
  registerUser(@Valid @RequestBody(required = false)
               SignUpRequest signUpRequest, Principal principal) {
    try {
      if (appUserService.isAlreadyExistForUser(signUpRequest.getUsername(),
                                               signUpRequest.getEmail())) {
        return new ApiErrorResponse(
            "Requested username or email is already exist",
            HttpStatus.CONFLICT);
      }
      AppUser appUser = (AppUser)ModelMap.convert(signUpRequest, AppUser.class);
      appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
      appUser.setRole(roleService.getByName(RoleName.ROLE_USER));
      appUser.setGroupName(
          appUserService.getByUsername(principal.getName()).getGroupName());
      appUserService.save(appUser);
      return new ApiSuccessResponse("User registration succeeded");
    } catch (Exception e) {
      return new ApiErrorResponse(e.getMessage(),
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/me")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public ResponseEntity<Object> getCurrentUser(Principal principal) {
    try {
      return new ApiSuccessResponse(new SanitizedAppUser(
          appUserService.getByUsername(principal.getName())));
    } catch (Exception e) {
      return new ApiErrorResponse(e.getMessage(),
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') AND @appUserService.isSameGroup(#id)")
  public ResponseEntity<Object> getUserById(@Valid @PathVariable long id) {
    try {
      return new ApiSuccessResponse(
          new SanitizedAppUser(appUserService.getById(id)));
    } catch (Exception e) {
      return new ApiErrorResponse(e.getMessage(),
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/all")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Object> getAllUsersOfSameGroup(Principal principal) {
    try {
      List<SanitizedAppUser> userList =
          appUserService
              .getAllByGroupName(
                  appUserService.getByUsername(principal.getName())
                      .getGroupName())
              .stream()
              .filter(Objects::nonNull)
              .map(SanitizedAppUser::new)
              .collect(Collectors.toList());
      return new ApiSuccessResponse(userList);
    } catch (Exception e) {
      return new ApiErrorResponse(e.getMessage(),
                                  HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
