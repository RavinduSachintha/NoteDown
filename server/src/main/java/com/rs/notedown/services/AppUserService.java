package com.rs.notedown.services;

import com.rs.notedown.exceptions.DataNotExistException;
import com.rs.notedown.models.AppUser;
import com.rs.notedown.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"UnusedDeclaration"})
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser getById(long id) {
//        return appUserRepository.findById(id).orElseThrow(() -> new DataNotExistException("User", id));
        return appUserRepository.findById(id).orElse(null);
    }

    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public void delete(long id) {
        appUserRepository.deleteById(id);
    }
}
