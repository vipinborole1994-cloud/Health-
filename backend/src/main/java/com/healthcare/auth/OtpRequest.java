package com.healthcare.auth;

import jakarta.validation.constraints.NotBlank;

public record OtpRequest(
        @NotBlank(message = "Mobile number is required") String mobile
) {
}
