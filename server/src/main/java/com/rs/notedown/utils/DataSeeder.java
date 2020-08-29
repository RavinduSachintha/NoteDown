package com.rs.notedown.utils;

import com.rs.notedown.constants.RoleName;
import com.rs.notedown.models.AppUser;
import com.rs.notedown.models.Role;
import com.rs.notedown.repositories.AppUserRepository;
import com.rs.notedown.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(
        prefix = "application.runner",
        value = "enabled",
        havingValue = "true",
        matchIfMissing = true)
@Component
@SuppressWarnings({"UnusedDeclaration"})
public class DataSeeder implements ApplicationRunner {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RoleRepository roleRepository;

    Role adminRole, appUserRole;
    AppUser adminUser, appUser;

    public void initializeData() {
        adminRole = new Role(RoleName.ROLE_ADMIN);
        appUserRole = new Role(RoleName.ROLE_USER);

        adminUser = new AppUser("admin", "admin", "admin@gmail.com", "admin@123", adminRole);
    }

    public void saveData() {
        try {
            if (roleRepository.count() == 0) {
                roleRepository.save(adminRole);
                roleRepository.save(appUserRole);
            }

            if (appUserRepository.count() == 0) {
                appUserRepository.save(adminUser);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        this.initializeData();
        this.saveData();
    }
}
