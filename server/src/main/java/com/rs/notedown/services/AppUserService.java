package com.rs.notedown.services;

import com.rs.notedown.exceptions.DataNotExistException;
import com.rs.notedown.models.AppUser;
import com.rs.notedown.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings({"UnusedDeclaration"})
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser getById(long id) {
        return appUserRepository.findById(id).orElseThrow(() -> new DataNotExistException("User", id));
    }

    public List<AppUser> getAllByGroupName(String groupName) {
        return appUserRepository.findByGroupName(groupName);
    }

    public AppUser getByUsername(String username) {
        return appUserRepository.findByUsername(username).orElseThrow(() -> new DataNotExistException("User", username));
    }

    public boolean isAlreadyExistForAdmin(String username, String email, String groupName) {
        return appUserRepository.findByUsernameOrEmailOrGroupName(username, email, groupName).isPresent();
    }

    public boolean isAlreadyExistForUser(String username, String email) {
        return appUserRepository.findByUsernameOrEmail(username, email).isPresent();
    }

    public boolean isSameGroup(long id) {
        AppUser currentUser = this.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.getById(id).getGroupName().equals(currentUser.getGroupName());
    }

    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public void delete(long id) {
        appUserRepository.deleteById(id);
    }
}
