package com.healthcare;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/")
    public Map<String, String> status() {
        return Map.of(
                "application", "Health Backend",
                "status", "running",
                "frontend", "Open health.html from the project folder",
                "authApi", "/api/auth"
        );
    }
}
