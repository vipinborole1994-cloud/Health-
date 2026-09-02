package com.healthcare.auth;

public record AuthResponse(
        String message,
        String token
) {
}
