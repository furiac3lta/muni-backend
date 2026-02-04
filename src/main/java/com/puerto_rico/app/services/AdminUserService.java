package com.puerto_rico.app.services;

import com.puerto_rico.app.services.AuditService;
import com.puerto_rico.app.core.ResourceNotFoundException;
import com.puerto_rico.app.security.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import com.puerto_rico.app.entities.AdminUser;
import com.puerto_rico.app.repositories.AdminUserRepository;
import com.puerto_rico.app.dto.AdminUserRequest;

@Service
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuditService auditService;

    public AdminUserService(
        AdminUserRepository adminUserRepository,
        PasswordEncoder passwordEncoder,
        AuditService auditService
    ) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.auditService = auditService;
    }

    public List<AdminUser> findAll() {
        return adminUserRepository.findAll();
    }

    public AdminUser create(AdminUserRequest request) {
        if (request.password() == null || request.password().isBlank()) {
            throw new IllegalArgumentException("Password requerido");
        }
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(request.username());
        adminUser.setPassword(passwordEncoder.encode(request.password()));
        adminUser.setRole(request.role());
        adminUser.setActive(request.active() == null || request.active());

        AdminUser saved = adminUserRepository.save(adminUser);
        auditService.log("AdminUser", "CREATE", SecurityUtils.currentUsername());
        return saved;
    }

    public AdminUser update(Long id, AdminUserRequest request) {
        AdminUser adminUser = adminUserRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Admin no encontrado"));

        adminUser.setUsername(request.username());
        adminUser.setRole(request.role());
        if (request.active() != null) {
            adminUser.setActive(request.active());
        }
        if (request.password() != null && !request.password().isBlank()) {
            adminUser.setPassword(passwordEncoder.encode(request.password()));
        }

        AdminUser saved = adminUserRepository.save(adminUser);
        auditService.log("AdminUser", "UPDATE", SecurityUtils.currentUsername());
        return saved;
    }
}
