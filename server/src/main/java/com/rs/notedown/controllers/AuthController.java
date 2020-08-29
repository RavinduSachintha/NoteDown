package com.rs.notedown.controllers;

import com.rs.notedown.models.AppUser;
import com.rs.notedown.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@Valid @PathVariable long id) {
        AppUser appUser = appUserService.getById(id);
        return ResponseEntity.ok(appUser);
    }
}
