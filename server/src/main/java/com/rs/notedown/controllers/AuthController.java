package com.rs.notedown.controllers;

import com.rs.notedown.payloads.ApiResponse;
import com.rs.notedown.payloads.SignInRequest;
import com.rs.notedown.payloads.SignInResponse;
import com.rs.notedown.payloads.SignUpRequest;
import com.rs.notedown.security.JwtTokenProvider;
import com.rs.notedown.services.AppUserService;
import com.rs.notedown.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private AuthService authService;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/sign-in")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {
        Authentication authentication
                = authService.authentication(signInRequest.getUsernameOrEmail(), signInRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return new ApiResponse(new SignInResponse(jwt));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return new ApiResponse("Hello");
    }
}
