package com.healthcare.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setValidator(validator)
                .setControllerAdvice(new InvalidOtpHandler())
                .build();
    }

    @Test
    void requestOtpDelegatesToService() throws Exception {
        when(authService.requestOtp("+15551234567"))
                .thenReturn(new AuthResponse("OTP requested", null));

        mockMvc.perform(post("/api/auth/request-otp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"mobile\":\"+15551234567\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OTP requested"))
                .andExpect(jsonPath("$.token").doesNotExist());

        verify(authService).requestOtp("+15551234567");
    }

    @Test
    void verifyOtpReturnsAuthenticationResponse() throws Exception {
        when(authService.verifyOtp("+15551234567", "1234"))
                .thenReturn(new AuthResponse("Authentication successful", "token"));

        mockMvc.perform(post("/api/auth/verify-otp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"mobile\":\"+15551234567\",\"otp\":\"1234\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Authentication successful"))
                .andExpect(jsonPath("$.token").value("token"));

        verify(authService).verifyOtp("+15551234567", "1234");
    }

    @Test
    void requestOtpRejectsBlankMobile() throws Exception {
        mockMvc.perform(post("/api/auth/request-otp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"mobile\":\" \"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void verifyOtpRejectsBlankFields() throws Exception {
        mockMvc.perform(post("/api/auth/verify-otp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"mobile\":\"\",\"otp\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @ControllerAdvice
    static class InvalidOtpHandler {
        @ExceptionHandler(IllegalArgumentException.class)
        ResponseEntity<Void> handleInvalidOtp() {
            return ResponseEntity.badRequest().build();
        }
    }
}
