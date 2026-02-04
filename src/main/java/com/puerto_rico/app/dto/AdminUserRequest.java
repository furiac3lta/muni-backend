package com.puerto_rico.app.dto;

import com.puerto_rico.app.enums.AdminRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminUserRequest(
    @NotBlank String username,
    String password,
    @NotNull AdminRole role,
    Boolean active
) {
}
