package com.rs.notedown.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"UnusedDeclaration"})
public class AuthService {
  @Autowired private AuthenticationManager authenticationManager;

  public Authentication authentication(String usernameOrEmail, String password)
      throws AuthenticationException {
    return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(usernameOrEmail, password));
  }
}
