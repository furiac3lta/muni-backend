package com.puerto_rico.app.security;

import com.puerto_rico.app.entities.AdminUser;
import com.puerto_rico.app.repositories.AdminUserRepository;
import com.puerto_rico.app.core.ResourceNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.puerto_rico.app.repositories.AdminUserRepository;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminUserRepository adminUserRepository;

    public AdminUserDetailsService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AdminUser adminUser = adminUserRepository.findByUsernameAndActiveTrue(username)
            .orElseThrow(() -> new ResourceNotFoundException("Admin no encontrado"));

        return User.withUsername(adminUser.getUsername())
            .password(adminUser.getPassword())
            .roles(adminUser.getRole().name())
            .disabled(!adminUser.isActive())
            .build();
    }
}
