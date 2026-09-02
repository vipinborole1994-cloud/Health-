package com.healthcare.auth;

import jakarta.validation.constraints.NotBlank;

public record OtpVerifyRequest(
        @NotBlank(message = "Mobile number is required") String mobile,
        @NotBlank(message = "OTP is required") String otp
) {
}
