package com.puerto_rico.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.puerto_rico.app.entities.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findByUsernameAndActiveTrue(String username);
}
