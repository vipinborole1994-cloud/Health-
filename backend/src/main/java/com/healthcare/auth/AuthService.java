package com.healthcare.auth;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private static final String DEVELOPMENT_OTP = "1234";
    private final ConcurrentMap<String, String> pendingOtps = new ConcurrentHashMap<>();

    public AuthResponse requestOtp(String mobile) {
        pendingOtps.put(mobile, DEVELOPMENT_OTP);
        return new AuthResponse("OTP requested", null);
    }

    public AuthResponse verifyOtp(String mobile, String otp) {
        String expectedOtp = pendingOtps.get(mobile);
        if (expectedOtp == null || !expectedOtp.equals(otp)) {
            throw new IllegalArgumentException("Invalid or expired OTP");
        }

        pendingOtps.remove(mobile);
        return new AuthResponse("Authentication successful", UUID.randomUUID().toString());
    }
}
