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
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@ConditionalOnProperty(
        prefix = "swagger",
        value = "enabled",
        havingValue = "false",
        matchIfMissing = true)
@CrossOrigin
@RestController
@RequestMapping("swagger-ui.html")
@SuppressWarnings({"UnusedDeclaration"})
public class DisabledSwaggerController {

    @RequestMapping(method = RequestMethod.GET)
    public void getSwagger(HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }

}
