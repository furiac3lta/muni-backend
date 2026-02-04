package com.puerto_rico.app.dto;

public record LoginResponse(
    String token,
    String username,
    String role
) {
}
