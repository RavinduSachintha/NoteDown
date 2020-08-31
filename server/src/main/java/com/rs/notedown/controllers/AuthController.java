package com.rs.notedown.controllers;

import com.rs.notedown.constants.RoleName;
import com.rs.notedown.models.AppUser;
import com.rs.notedown.payloads.*;
import com.rs.notedown.security.JwtTokenProvider;
import com.rs.notedown.services.AppUserService;
import com.rs.notedown.services.AuthService;
import com.rs.notedown.services.RoleService;
import com.rs.notedown.utils.ModelMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/auth")
@SuppressWarnings({"UnusedDeclaration"})
public class AuthController {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/sign-in")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {
        try {
            Authentication authentication
                    = authService.authentication(signInRequest.getUsernameOrEmail(), signInRequest.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);
            return new ApiSuccessResponse(new SignInResponse(jwt));
        } catch (Exception e) {
            return new ApiErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> registerAdminUser(@Valid @RequestBody SignUpRequestForAdmin signUpRequestForAdmin) {
        try {
            if (appUserService.isAlreadyExistForAdmin(signUpRequestForAdmin.getUsername(),
                    signUpRequestForAdmin.getEmail(), signUpRequestForAdmin.getGroupName())) {
                return new ApiErrorResponse
                        ("Requested username or email or group name is already exist", HttpStatus.CONFLICT);
            }
            AppUser appUser = (AppUser) ModelMap.convert(signUpRequestForAdmin, AppUser.class);
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            appUser.setRole(roleService.getByName(RoleName.ROLE_ADMIN));
            appUserService.save(appUser);
            return new ApiSuccessResponse("Admin user registration succeeded");
        } catch (Exception e) {
            return new ApiErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
