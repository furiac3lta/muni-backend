package com.puerto_rico.app.controllers;

import com.puerto_rico.app.core.ApiResponse;
import com.puerto_rico.app.dto.AdminUserRequest;
import com.puerto_rico.app.entities.AdminUser;
import com.puerto_rico.app.services.AdminUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AdminUser>>> findAll() {
        return ResponseEntity.ok(ApiResponse.ok("Usuarios admin", adminUserService.findAll()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AdminUser>> create(@Valid @RequestBody AdminUserRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Admin creado", adminUserService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AdminUser>> update(
        @PathVariable Long id,
        @Valid @RequestBody AdminUserRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.ok("Admin actualizado", adminUserService.update(id, request)));
    }
}
