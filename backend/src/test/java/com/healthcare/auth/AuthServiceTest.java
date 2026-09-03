package com.healthcare.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

class AuthServiceTest {
    private final AuthService service = new AuthService();

    @Test
    void requestOtpReturnsConfirmationWithoutToken() {
        AuthResponse response = service.requestOtp("+15551234567");

        assertThat(response).isEqualTo(new AuthResponse("OTP requested", null));
    }

    @Test
    void verifyOtpReturnsTokenAndConsumesOtp() {
        service.requestOtp("+15551234567");

        AuthResponse response = service.verifyOtp("+15551234567", "1234");

        assertThat(response.message()).isEqualTo("Authentication successful");
        assertThat(response.token()).isNotBlank();
        assertThatThrownBy(() -> service.verifyOtp("+15551234567", "1234"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid or expired OTP");
    }

    @Test
    void verifyOtpRejectsUnknownMobileOrWrongOtp() {
        service.requestOtp("+15551234567");

        assertThatThrownBy(() -> service.verifyOtp("+15550000000", "1234"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid or expired OTP");
        assertThatThrownBy(() -> service.verifyOtp("+15551234567", "0000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid or expired OTP");
    }
}
