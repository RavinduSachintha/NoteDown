package com.rs.notedown.services;

import com.rs.notedown.exceptions.DataNotExistException;
import com.rs.notedown.models.AppUser;
import com.rs.notedown.repositories.AppUserRepository;
import com.rs.notedown.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@SuppressWarnings({"UnusedDeclaration"})
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Let people login with either username or email
        AppUser appUser = appUserRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow
                (() -> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));
        return UserPrincipal.create(appUser);
    }

    public UserDetails loadUserById(long id) throws DataNotExistException {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(
                () -> new DataNotExistException("User", id)
        );
        return UserPrincipal.create(appUser);
    }
}
